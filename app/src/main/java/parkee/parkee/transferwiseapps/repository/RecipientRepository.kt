package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.recipient.RecipientService

class RecipientRepository(private val recipientService: RecipientService) {

    suspend fun getFormRequirments(source: String, target: String) =
        recipientService.getFormRequirments(source, target)

    suspend fun createRecipient(parameter: Map<String,Any>) = recipientService.createRecipient(parameter)

    suspend fun validateSortCode(sortCode: String) =
        recipientService.validateSortCode(sortCode)

    suspend fun validateSortCodeAccountNumber(accountNumber: String) =
        recipientService.validateSortCodeAccountNumber(accountNumber)

    suspend fun validateIbanAndBic(iban: String, bic: String) =
        recipientService.validateIbanAndBic(iban, bic)

    suspend fun validateAbartn(abartn: String) =
        recipientService.validateAbartn(abartn)

    suspend fun validateAbaAccountNumber(abaAccountNumber: String) =
        recipientService.validateAbaAccountNumber(abaAccountNumber)

    suspend fun validateIfscCode(ifscCode: String) = recipientService.validateIfscCode(ifscCode)

    suspend fun validateIndianAccountNumberCode(accountNumber: String) =
        recipientService.validateIndianAccountNumber(accountNumber)

    suspend fun validateBsbCode(bsbCode: String) = recipientService.validateBsbCode(bsbCode)

    suspend fun validateAustralianAccountNumberCode(accountNumber: String) =
        recipientService.validateAustralianAccountNumber(accountNumber)

    suspend fun validateTransitNumber(institutionNumber: String, transitNumber: String) =
        recipientService.validateCanadianTransitNumber(institutionNumber, transitNumber)

    suspend fun validateBankGiroNumber(bankGiroNumber: String) =
        recipientService.validateBankGiroNumber(bankGiroNumber)

    suspend fun validateHungarianAccountNumber(accountNumber: String) =
        recipientService.validateHungarianAccountNumber(accountNumber)

    suspend fun validatePolishAccountNumber(accountNumber: String) =
        recipientService.validatePolishAccountNumber(accountNumber)

    suspend fun validatePrivatPhoneNumber(phoneNumber: String) =
        recipientService.validatePrivatBankPhoneNumber(phoneNumber)

    suspend fun validatePrivatAccounteNumber(accountNumber: String) =
        recipientService.validatePrivatBankAccountNumber(accountNumber)

    suspend fun validateNewZealandAccounteNumber(accountNumber: String) =
        recipientService.validateNewZealandAccountNumber(accountNumber)

    suspend fun validateEmiratesBicCode(bic: String, iban: String) =
        recipientService.validateEmiratesBic(bic, iban)

    suspend fun validateChineseCardNumber(accountNumber: String) =
        recipientService.validateChineseCardNumber(accountNumber)

    suspend fun validateThailandAccountNumber(bankCode: String, accountNumber: String) =
        recipientService.validateThailandAccountNumber(bankCode, accountNumber)
}