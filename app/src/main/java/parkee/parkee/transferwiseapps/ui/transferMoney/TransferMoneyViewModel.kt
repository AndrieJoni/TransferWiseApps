package parkee.parkee.transferwiseapps.ui.transferMoney

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.data.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.data.network.quote.CreateQuoteResponseDto
import parkee.parkee.transferwiseapps.data.network.recipient.RecipientDto
import parkee.parkee.transferwiseapps.data.network.transfer.CreateFundResponseDto
import parkee.parkee.transferwiseapps.data.network.transfer.CreateTransferResponseDto
import parkee.parkee.transferwiseapps.data.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.data.repository.*
import parkee.parkee.transferwiseapps.domain.mapToRecipientModel
import parkee.parkee.transferwiseapps.uiModel.CurrencyModel
import parkee.parkee.transferwiseapps.uiModel.RecipientModel
import parkee.parkee.transferwiseapps.uiModel.TransferConfirmationModel
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent
import java.util.*
import kotlin.math.roundToInt

class TransferMoneyViewModel(
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val quoteRepository: QuoteRepository,
    private val userProfilesRepository: UserProfilesRepository,
    private val recipientRepository: RecipientRepository,
    private val transferRepository: TransferRepository
) : ViewModel() {

    var setTargetAmount = SingleLiveEvent<Long>()
    var setFeeAmount = SingleLiveEvent<Long>()
    var setRecipientList = MutableLiveData<List<RecipientModel>>()
    var setTransferConfirmationDetail = SingleLiveEvent<TransferConfirmationModel>()
    var setTargetCurrency = SingleLiveEvent<String>()

    var sourceCurrencyErrorEvent = SingleLiveEvent<Boolean>()
    var targetCurrencyErrorEvent = SingleLiveEvent<Boolean>()
    var sourceAmountErrorEvent = SingleLiveEvent<Boolean>()

    var goToPageEvent = SingleLiveEvent<Int>()
    var goBackWithResult = SingleLiveEvent<Any>()

    private var currentSourceAmount = 0
    private var currentTargetAmount = 0
    private var currentGuaranteeRate: Double = 0.0
    private var currentTransferFee: Double = 0.0
    private var currentArriveTime: String = ""

    private var currentSourceCurrency: CurrencyModel? = null
    private var currentTargetCurrency: CurrencyModel? = null
    private var currentRecipient: RecipientModel? = null
    private var currentQuotesId = 0

    fun handleSourceTextWatcher(amount: String) {

        if (amount.isNotEmpty() and !amount.isBlank()) {

            sourceAmountErrorEvent.value = false
            currentSourceAmount = amount.toInt()

            getTemporaryQuotes()

        } else {

            sourceAmountErrorEvent.value = true
            currentSourceAmount = 0
        }
    }

    fun sourceCurrencyChange(currency: CurrencyModel) {

        currentSourceCurrency = currency

        if (currentSourceAmount > 0) {
            sourceCurrencyErrorEvent.value = false
            getTemporaryQuotes()
        } else {
            sourceCurrencyErrorEvent.value = true
        }
    }

    fun targetCurrencyChange(currency: CurrencyModel) {

        currentTargetCurrency = currency

        if (currentSourceAmount > 0) {
            targetCurrencyErrorEvent.value = false
            getTemporaryQuotes()
        } else {
            targetCurrencyErrorEvent.value = true
        }
    }

    fun recipientChoosen(recipientModel: RecipientModel) {
        currentRecipient = recipientModel
        createQuotes()
    }

    fun onTabSelected(position: Int) {

        if (position == 1) {

            currentTargetCurrency = currentSourceCurrency

            setTargetCurrency.value = currentSourceCurrency?.currencyName

            getTemporaryQuotes()

        } else if (position == 0) {

            currentTargetCurrency = null

            setTargetCurrency.value = ""
            setFeeAmount.value = 0
            setTargetAmount.value = 0
        }
    }

    fun reasonConfirmed() {
        goToPageEvent.value = 3
    }

    fun chooseAndConvertFinish() {

        when {
            currentSourceAmount == 0 -> {
                sourceAmountErrorEvent.value = true
            }
            currentSourceCurrency == null -> {
                sourceCurrencyErrorEvent.value = true
            }
            currentTargetCurrency == null -> {
                targetCurrencyErrorEvent.value = true
            }
            else -> {
                goToPageEvent.value = 1
            }
        }
    }

    fun showTransferConfirmationDetail() {

        setTransferConfirmationDetail.value = TransferConfirmationModel(
            currentSourceAmount,
            currentTargetAmount,
            currentTransferFee,
            currentGuaranteeRate,
            currentSourceCurrency,
            currentTargetCurrency,
            currentArriveTime,
            currentRecipient
        )
    }

    private fun createQuotes() {

        viewModelScope.launch {

            try {

                var userProfilesPersonalDto: List<UserProfilesPersonalDto> = listOf()

                withContext(Dispatchers.IO) {
                    userProfilesPersonalDto = userProfilesRepository.getUserProfiles()
                }

                if (!userProfilesPersonalDto.isNullOrEmpty()) {

                    var accountBalanceDto: List<AccountBalanceDto>? = listOf()

                    withContext(Dispatchers.IO) {
                        accountBalanceDto =
                            borderlessAccountsRepository
                                .getAccountBalance(userProfilesPersonalDto[0].id.toString())
                    }

                    var createQuoteResponseDto: CreateQuoteResponseDto? = null

                    withContext(Dispatchers.IO) {

                        val parameter = mutableMapOf<String, Any>()

                        parameter["profileId"] = userProfilesPersonalDto[0].id
                        parameter["source"] = (currentSourceCurrency?.currencyName ?: "")
                        parameter["target"] = (currentTargetCurrency?.currencyName ?: "")
                        parameter["rateType"] = "FIXED"
                        parameter["sourceAmount"] = currentSourceAmount
                        parameter["type"] = "BALANCE_PAYOUT"

                        createQuoteResponseDto = quoteRepository.getQuote(
                            parameter
                        )
                    }

                    if (createQuoteResponseDto != null) {

                        currentQuotesId = createQuoteResponseDto!!.id
                        currentTargetAmount = createQuoteResponseDto!!.targetAmount.roundToInt()
                        currentGuaranteeRate = createQuoteResponseDto!!.rate
                        currentTransferFee = createQuoteResponseDto!!.fee
                        currentArriveTime = createQuoteResponseDto!!.deliveryEstimate

                        goToPageEvent.value = 2
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getTemporaryQuotes() {

        viewModelScope.launch {

            try {

                var createQuoteResponseDto: CreateQuoteResponseDto? = null

                withContext(Dispatchers.IO) {

                    val parameter = mutableMapOf<String, Any>()

                    parameter["source"] = (currentSourceCurrency?.currencyName ?: "")
                    parameter["target"] = (currentTargetCurrency?.currencyName ?: "")
                    parameter["rateType"] = "FIXED"
                    parameter["sourceAmount"] = currentSourceAmount
                    parameter["type"] = "BALANCE_CONVERSION"

                    createQuoteResponseDto = quoteRepository.getQuote(
                        parameter
                    )
                }

                if (createQuoteResponseDto != null) {
                    setTargetAmount.value = createQuoteResponseDto!!.targetAmount.toLong()
                    setFeeAmount.value = createQuoteResponseDto!!.fee.toLong()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAllRecipient() {

        viewModelScope.launch {

            try {

                var recipientData: List<RecipientDto>? = null

                withContext(Dispatchers.IO) {
                    recipientData = recipientRepository.getRecipientWithQuery(
                        currentTargetCurrency?.currencyName.toString()
                    )
                }

                if (recipientData != null) {
                    setRecipientList.value = recipientData?.mapToRecipientModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun buttonPayClicked() {

        viewModelScope.launch {

            try {

                var userProfilesPersonalDto: List<UserProfilesPersonalDto> = listOf()

                withContext(Dispatchers.IO) {
                    userProfilesPersonalDto = userProfilesRepository.getUserProfiles()
                }

                if (!userProfilesPersonalDto.isNullOrEmpty()) {

                    var createTransferResponseDto: CreateTransferResponseDto? = null

                    withContext(Dispatchers.IO) {

                        val parameter = mutableMapOf<String, Any>()

                        parameter["targetAccount"] = currentRecipient?.id.toString()
                        parameter["quote"] = currentQuotesId
                        parameter["customerTransactionId"] = UUID.randomUUID().toString()
                        parameter["details"] = mapOf(
                            "reference" to "to my friend",
                            "transferPurpose" to "verification.transfers.purpose.pay.bills",
                            "sourceOfFunds" to "verification.source.of.funds.other"
                        )

                        createTransferResponseDto = transferRepository.createTransfer(parameter)
                    }

                    if (createTransferResponseDto != null) {

                        var createFundResponseDto: CreateFundResponseDto? = null

                        withContext(Dispatchers.IO) {
                            createFundResponseDto = transferRepository.createFund(
                                userProfilesPersonalDto[0].id.toString(),
                                createTransferResponseDto?.id.toString(),
                                mapOf("type" to "BALANCE")
                            )
                        }

                        if (createFundResponseDto != null && createFundResponseDto!!.status == "COMPLETED") {
                            goBackWithResult.call()
                        }
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}