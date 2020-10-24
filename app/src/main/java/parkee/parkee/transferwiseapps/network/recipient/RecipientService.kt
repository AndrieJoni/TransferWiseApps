package parkee.parkee.transferwiseapps.network.recipient

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@JvmSuppressWildcards
interface RecipientService {

    @GET("v1/account-requirements")
    suspend fun getFormRequirments(
        @Query("source") source: String,
        @Query("target") target: String,
        @Query("sourceAmount") amount: Int = 1000,
    ): List<FormRequirementsDto>

    @POST("v1/accounts")
    suspend fun createRecipient(
        @Body parameter: Map<String, Any>
    ): CreateRecipietResponseDto

    @POST("v1/accounts")
    suspend fun getAllRecipient(
    ): List<CreateRecipietResponseDto>

    @GET("v1/validators/sort-code")
    suspend fun validateSortCode(@Query("sortCode") sortCode: String): ValidationRequirementsDto

    @GET("v1/validators/sort-code-account-number")
    suspend fun validateSortCodeAccountNumber(@Query("accountNumber") accountNumber: String): ValidationRequirementsDto

    @GET("v1/validators/iban")
    suspend fun validateIban(@Query("iban") iban: String): ValidationRequirementsDto

    @GET("v1/validators/bic")
    suspend fun validateIbanAndBic(
        @Query("bic") bic: String,
        @Query("iban") iban: String
    ): ValidationRequirementsDto

    @GET("v1/validators/abartn")
    suspend fun validateAbartn(@Query("abartn") abartn: String): ValidationRequirementsDto

    @GET("v1/validators/aba-account-number")
    suspend fun validateAbaAccountNumber(@Query("accountNumber") accountNumber: String): ValidationRequirementsDto

    @GET("v1/validators/ifsc-code")
    suspend fun validateIfscCode(@Query("ifscCode") ifscCode: String): ValidationRequirementsDto

    @GET("v1/validators/indian-account-number")
    suspend fun validateIndianAccountNumber(@Query("accountNumber") accountNumber: String): ValidationRequirementsDto

    @GET("v1/validators/bsb-code")
    suspend fun validateBsbCode(@Query("bsbCode") accountNumber: String): ValidationRequirementsDto

    @GET("v1/validators/australian-account-number")
    suspend fun validateAustralianAccountNumber(@Query("accountNumber") accountNumber: String): ValidationRequirementsDto

    @GET("v1/validators/canadian-institution-number")
    suspend fun validateCanadianInstituionNumber(@Query("institutionNumber") instituionNumber: String): ValidationRequirementsDto

    @GET("v1/validators/canadian-transit-number")
    suspend fun validateCanadianTransitNumber(
        @Query("institutionNumber") instituionNumber: String,
        @Query("transitNumber") transitNumber: String
    ): ValidationRequirementsDto

    @GET("v1/validators/canadian-account-number")
    suspend fun validateCanadianAccountNumber(
        @Query("institutionNumber") instituionNumber: String,
        @Query("transitNumber") transitNumber: String,
        @Query("accountNumber") accountNumber: String
    ): ValidationRequirementsDto

    @GET("v1/validators/bankgiro-number")
    suspend fun validateBankGiroNumber(
        @Query("bankgiroNumber") bankgiroNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/hungarian-account-number")
    suspend fun validateHungarianAccountNumber(
        @Query("accountNumber") accountNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/polish-account-number")
    suspend fun validatePolishAccountNumber(
        @Query("accountNumber") accountNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/privatbank-account-number")
    suspend fun validatePrivatBankAccountNumber(
        @Query("accountNumber") accountNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/privatbank-phone-number")
    suspend fun validatePrivatBankPhoneNumber(
        @Query("phoneNumber") phoneNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/new-zealand-account-number")
    suspend fun validateNewZealandAccountNumber(
        @Query("accountNumber") accountNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/emirates-bic")
    suspend fun validateEmiratesBic(
        @Query("bic") bic: String,
        @Query("iban") iban: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/chinese-card-number")
    suspend fun validateChineseCardNumber(
        @Query("cardNumber") cardNumber: String,
    ): ValidationRequirementsDto

    @GET("v1/validators/thailand-account-number")
    suspend fun validateThailandAccountNumber(
        @Query("accountNumber") accountNumber: String,
        @Query("bankCode") bankCode: String,
    ): ValidationRequirementsDto
}