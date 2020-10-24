package parkee.parkee.transferwiseapps.ui.transferMoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.network.quote.CreateQuoteResponseDto
import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.QuoteRepository
import parkee.parkee.transferwiseapps.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class TransferMoneyViewModel(
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val quoteRepository: QuoteRepository,
    private val userProfilesRepository: UserProfilesRepository,
) : ViewModel() {

    var setTargetAmount = SingleLiveEvent<Double>()
    var setFeeAmount = SingleLiveEvent<Double>()

    var currentSourceAmount = 0

    private var currenctSourceCurrency: CurrencyModel? = null
    private var currenctTargetCurrency: CurrencyModel? = null

    fun handleSourceTextWatcher(amount: String) {
        if (!amount.isEmpty() and !amount.isBlank()) {
            currentSourceAmount = amount.toInt()
            getTemporaryQuotes()
        } else {
            currentSourceAmount = 0
        }
    }

    fun sourceCurrencyChange(currency: CurrencyModel) {
        currenctSourceCurrency = currency

        if (currentSourceAmount > 0) {
            getTemporaryQuotes()
        }
    }

    fun targetCurrencyChange(currency: CurrencyModel) {
        currenctTargetCurrency = currency

        if (currentSourceAmount > 0) {
            getTemporaryQuotes()
        }
    }

    private fun getTemporaryQuotes() {

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

                    withContext(Dispatchers.IO) {
                        userProfilesPersonalDto
                    }

                    var createQuoteResponseDto: CreateQuoteResponseDto? = null

                    withContext(Dispatchers.IO) {
                        createQuoteResponseDto = quoteRepository.getTemporaryQuote(
                            mapOf(
                                "profileId" to (accountBalanceDto?.get(0)?.profileId ?: ""),
                                "source" to (currenctSourceCurrency?.currencyName ?: ""),
                                "target" to (currenctTargetCurrency?.currencyName ?: ""),
                                "rateType" to "FIXED",
                                "sourceAmount" to currentSourceAmount,
                                "type" to "BALANCE_PAYOUT"
                            )
                        )
                    }

                    if (createQuoteResponseDto != null) {
                        setTargetAmount.value = createQuoteResponseDto!!.targetAmount
                        setFeeAmount.value = createQuoteResponseDto!!.fee
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}