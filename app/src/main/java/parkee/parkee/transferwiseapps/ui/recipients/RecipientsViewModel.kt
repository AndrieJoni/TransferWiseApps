package parkee.parkee.transferwiseapps.ui.recipients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import parkee.parkee.transferwiseapps.domain.mapToRecipientBankDetailsModel
import parkee.parkee.transferwiseapps.domain.mapToRecipientModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.network.recipient.RecipientDto
import parkee.parkee.transferwiseapps.network.recipient.FormRequirementsDto
import parkee.parkee.transferwiseapps.network.recipient.ValidationRequirementsDto
import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.RecipientRepository
import parkee.parkee.transferwiseapps.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.ui.FieldRequirementsModel
import parkee.parkee.transferwiseapps.ui.RecipientBankDetailsModel
import parkee.parkee.transferwiseapps.ui.RecipientModel
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class RecipientsViewModel(
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val recipientRepository: RecipientRepository,
    private val userProfilesRepository: UserProfilesRepository
) : ViewModel() {

    var setRecipientBankDetailsEvent = SingleLiveEvent<List<RecipientBankDetailsModel>>()
    var showFieldRequirementsEvent = SingleLiveEvent<List<FieldRequirementsModel>>()

    var currentCurrencySelected: CurrencyModel? = null

    var setRecipientList = MutableLiveData<List<RecipientModel>>()

    private fun getFormRequirements(currency: String) {

        viewModelScope.launch {

            try {

                var formRequirmentsDto: List<FormRequirementsDto>? = null

                withContext(Dispatchers.IO) {
                    formRequirmentsDto = recipientRepository.getFormRequirments(
                        "IDR", currency
                    )
                }

                if (formRequirmentsDto != null) {
                    setRecipientBankDetailsEvent.value =
                        formRequirmentsDto!!.mapToRecipientBankDetailsModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun currencySelected(data: CurrencyModel) {
        getFormRequirements(data.currencyName)
    }

    fun recipientBankDetailsSelected(data: RecipientBankDetailsModel) {
        showFieldRequirementsEvent.value = data.fields.subList(1, data.fields.size)
    }

    fun addRecipient(
        firstName: String,
        lastName: String,
        legalType: String,
        recipientBankDetailsModel: RecipientBankDetailsModel,
        field: List<FieldRequirementsModel>
    ) {

        viewModelScope.launch {

            var isValid = false

            try {

                when (currentCurrencySelected?.currencyName) {

                    "GBP" -> {
                        isValid =
                            checkGbpValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "BGN", "CHF", "DKK", "EUR", "GEL", "NOK", "PKR", "RON" -> {
                        isValid = checkIbanAndBicValidation(
                            field[0].selectedValues,
                            field[1].selectedValues
                        )
                    }

                    "USD" -> {
                        isValid =
                            checkUsdValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "INR" -> {
                        isValid =
                            checkIrnValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "AUD" -> {
                        isValid =
                            checkAudValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "CAD" -> {
                        isValid =
                            checkCadValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "SEK" -> {
                        isValid = checkSekValidation(field[0].selectedValues)
                    }

                    "HUF" -> {
                        isValid = checkHufValidation(field[0].selectedValues)
                    }

                    "PLN" -> {
                        isValid = checkPlnValidation(field[0].selectedValues)
                    }

                    "UAH" -> {
                        isValid =
                            checkUahValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "NZD" -> {
                        isValid = checkNzdValidation(field[0].selectedValues)
                    }

                    "AED" -> {
                        isValid =
                            checkAedValidation(field[0].selectedValues, field[1].selectedValues)
                    }

                    "CNY" -> {
                        isValid = checkCnyValidation(field[0].selectedValues)
                    }

                    "THB" -> {
                        isValid =
                            checkThbValidation(field[0].selectedValues, field[1].selectedValues)
                    }
                }

                if (isValid) {
                    createRecipient(
                        firstName,
                        lastName,
                        legalType,
                        recipientBankDetailsModel,
                        field
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun createRecipient(
        firstName: String,
        lastName: String,
        legalType: String,
        recipientBankDetailsModel: RecipientBankDetailsModel,
        field: List<FieldRequirementsModel>
    ) {

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

            var recipientDto: RecipientDto? = null

            val paramater = mutableMapOf<String, Any>()

            paramater["currency"] = currentCurrencySelected?.currencyName.toString()
            paramater["type"] = recipientBankDetailsModel.fieldsType
            paramater["profile"] = accountBalanceDto?.get(0)?.profileId.toString()
            paramater["accountHolderName"] = String.format("%s %s", firstName, lastName)

            val paramaterDetails = mutableMapOf<String, String>()

            paramaterDetails["legalType"] = legalType
            for (details in field) {
                paramaterDetails[details.fieldKey] = details.selectedValues
            }

            paramater["details"] = paramaterDetails

            withContext(Dispatchers.IO) {
                recipientDto = recipientRepository.createRecipient(paramater)
            }

            if (recipientDto != null) {

            }
        }
    }

    private suspend fun checkGbpValidation(
        sortCode: String,
        sortCodeAccountNumber: String
    ): Boolean {

        var isSortCodeValid = false
        var isSortCodeAccountNumberValid = false

        var responseSortCode: Deferred<ValidationRequirementsDto>? = null
        var responseSortCodeAccountNumber: Deferred<ValidationRequirementsDto>? = null

        withContext(Dispatchers.IO) {

            responseSortCode = async {
                recipientRepository.validateSortCode(sortCode)
            }

            responseSortCodeAccountNumber = async {
                recipientRepository.validateSortCodeAccountNumber(sortCodeAccountNumber)
            }
        }

        if (responseSortCode?.await()?.validation == "success") {
            isSortCodeValid = true
        }

        if (responseSortCodeAccountNumber?.await()?.validation == "success") {
            isSortCodeAccountNumberValid = true
        }

        return isSortCodeValid && isSortCodeAccountNumberValid
    }

    private suspend fun checkIbanAndBicValidation(
        iban: String,
        bic: String
    ): Boolean {

        var isIbanAndBicValid = false

        withContext(Dispatchers.IO) {

            isIbanAndBicValid =
                recipientRepository.validateIbanAndBic(iban, bic).validation == "success"
        }

        return isIbanAndBicValid
    }

    private suspend fun checkUsdValidation(
        abartn: String,
        abaAccountNumber: String
    ): Boolean {

        var isAbartnValid = false
        var isAbaAccountNumberValid = false

        var responseAbartn: Deferred<ValidationRequirementsDto>? = null
        var responseAbaAccountNumber: Deferred<ValidationRequirementsDto>? = null

        withContext(Dispatchers.IO) {

            responseAbartn = async {
                recipientRepository.validateAbartn(abartn)
            }

            responseAbaAccountNumber = async {
                recipientRepository.validateAbaAccountNumber(abaAccountNumber)
            }
        }

        if (responseAbartn?.await()?.validation == "success") {
            isAbartnValid = true
        }

        if (responseAbaAccountNumber?.await()?.validation == "success") {
            isAbaAccountNumberValid = true
        }

        return isAbartnValid && isAbaAccountNumberValid
    }

    private suspend fun checkIrnValidation(
        ifscCode: String,
        accountNumber: String
    ): Boolean {

        var isIfscCodeValid = false
        var isAccountNumberValid = false

        var responseIfscCode: Deferred<ValidationRequirementsDto>? = null
        var responseAccountNumber: Deferred<ValidationRequirementsDto>? = null

        withContext(Dispatchers.IO) {

            responseIfscCode = async {
                recipientRepository.validateIfscCode(ifscCode)
            }

            responseAccountNumber = async {
                recipientRepository.validateIndianAccountNumberCode(accountNumber)
            }
        }

        if (responseIfscCode?.await()?.validation == "success") {
            isIfscCodeValid = true
        }

        if (responseAccountNumber?.await()?.validation == "success") {
            isAccountNumberValid = true
        }

        return isIfscCodeValid && isAccountNumberValid
    }

    private suspend fun checkAudValidation(
        bsbCode: String,
        accountNumber: String
    ): Boolean {

        var isBsbCodeValid = false
        var isAccountNumberValid = false

        var responseBsbCode: Deferred<ValidationRequirementsDto>? = null
        var responseAccountNumber: Deferred<ValidationRequirementsDto>? = null

        withContext(Dispatchers.IO) {

            responseBsbCode = async {
                recipientRepository.validateBsbCode(bsbCode)
            }

            responseAccountNumber = async {
                recipientRepository.validateAustralianAccountNumberCode(accountNumber)
            }
        }

        if (responseBsbCode?.await()?.validation == "success") {
            isBsbCodeValid = true
        }

        if (responseAccountNumber?.await()?.validation == "success") {
            isAccountNumberValid = true
        }

        return isBsbCodeValid && isAccountNumberValid
    }

    private suspend fun checkCadValidation(
        instituionNumber: String,
        transitNumber: String
    ): Boolean {

        var isInstitutionAndTransitValid = false

        withContext(Dispatchers.IO) {

            isInstitutionAndTransitValid =
                recipientRepository.validateTransitNumber(
                    instituionNumber,
                    transitNumber
                ).validation == "success"
        }

        return isInstitutionAndTransitValid
    }

    private suspend fun checkSekValidation(
        bankGiroNumber: String
    ): Boolean {

        var isBankGiroNumberValid = false

        withContext(Dispatchers.IO) {

            isBankGiroNumberValid =
                recipientRepository.validateBankGiroNumber(
                    bankGiroNumber
                ).validation == "success"
        }

        return isBankGiroNumberValid
    }

    private suspend fun checkHufValidation(
        accountNumber: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validateHungarianAccountNumber(
                    accountNumber
                ).validation == "success"
        }

        return isAccountNumberValid
    }

    private suspend fun checkPlnValidation(
        accountNumber: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validatePolishAccountNumber(
                    accountNumber
                ).validation == "success"
        }

        return isAccountNumberValid
    }

    private suspend fun checkUahValidation(
        phoneNumber: String,
        accountNumber: String
    ): Boolean {

        var isPhoneNumberValid = false
        var isAccountNumberValid = false

        var responsePhoneNumber: Deferred<ValidationRequirementsDto>? = null
        var responseAccountNumber: Deferred<ValidationRequirementsDto>? = null

        withContext(Dispatchers.IO) {

            responsePhoneNumber = async {
                recipientRepository.validatePrivatPhoneNumber(phoneNumber)
            }

            responseAccountNumber = async {
                recipientRepository.validatePrivatAccounteNumber(accountNumber)
            }
        }

        if (responsePhoneNumber?.await()?.validation == "success") {
            isPhoneNumberValid = true
        }

        if (responseAccountNumber?.await()?.validation == "success") {
            isAccountNumberValid = true
        }

        return isPhoneNumberValid && isAccountNumberValid
    }

    private suspend fun checkNzdValidation(
        accountNumber: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validateNewZealandAccounteNumber(
                    accountNumber
                ).validation == "success"
        }

        return isAccountNumberValid
    }

    private suspend fun checkAedValidation(
        bic: String,
        iban: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validateEmiratesBicCode(
                    bic, iban
                ).validation == "success"
        }

        return isAccountNumberValid
    }

    private suspend fun checkCnyValidation(
        accountNumber: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validateChineseCardNumber(
                    accountNumber
                ).validation == "success"
        }

        return isAccountNumberValid
    }


    private suspend fun checkThbValidation(
        bankCode: String,
        accountNumber: String
    ): Boolean {

        var isAccountNumberValid = false

        withContext(Dispatchers.IO) {

            isAccountNumberValid =
                recipientRepository.validateThailandAccountNumber(
                    bankCode,
                    accountNumber
                ).validation == "success"
        }

        return isAccountNumberValid
    }

    fun getAllRecipient() {

        viewModelScope.launch {

            try {

                var recipientData: List<RecipientDto>? = null

                withContext(Dispatchers.IO) {
                    recipientData = recipientRepository.getAllRecipient()
                }

                if (recipientData != null) {
                    setRecipientList.value = recipientData?.mapToRecipientModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}