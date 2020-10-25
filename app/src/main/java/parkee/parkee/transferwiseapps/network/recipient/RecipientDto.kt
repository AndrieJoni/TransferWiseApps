package parkee.parkee.transferwiseapps.network.recipient

import com.google.gson.annotations.SerializedName
import parkee.parkee.transferwiseapps.network.user.UserAddressDto

data class RecipientDto(
    @SerializedName("accountHolderName")
    var accountHolderName: String = "",
    @SerializedName("active")
    var active: Boolean = false,
    @SerializedName("business")
    var business: Any = Any(),
    @SerializedName("country")
    var country: String = "",
    @SerializedName("currency")
    var currency: String = "",
    @SerializedName("details")
    var details: Details = Details(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("ownedByCustomer")
    var ownedByCustomer: Boolean = false,
    @SerializedName("profile")
    var profile: Int = 0,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("user")
    var user: Int = 0
)

data class Details(
    @SerializedName("abartn")
    var abartn: Any = Any(),
    @SerializedName("accountNumber")
    var accountNumber: String = "",
    @SerializedName("accountType")
    var accountType: Any = Any(),
    @SerializedName("address")
    var address: UserAddressDto = UserAddressDto(),
    @SerializedName("BIC")
    var bIC: Any = Any(),
    @SerializedName("bankCode")
    var bankCode: Any = Any(),
    @SerializedName("bankName")
    var bankName: Any = Any(),
    @SerializedName("bankgiroNumber")
    var bankgiroNumber: Any = Any(),
    @SerializedName("bic")
    var bic: Any = Any(),
    @SerializedName("branchCode")
    var branchCode: Any = Any(),
    @SerializedName("branchName")
    var branchName: Any = Any(),
    @SerializedName("bsbCode")
    var bsbCode: Any = Any(),
    @SerializedName("businessNumber")
    var businessNumber: Any = Any(),
    @SerializedName("cardNumber")
    var cardNumber: Any = Any(),
    @SerializedName("city")
    var city: Any = Any(),
    @SerializedName("clabe")
    var clabe: Any = Any(),
    @SerializedName("clearingNumber")
    var clearingNumber: Any = Any(),
    @SerializedName("cnpj")
    var cnpj: Any = Any(),
    @SerializedName("cpf")
    var cpf: Any = Any(),
    @SerializedName("dateOfBirth")
    var dateOfBirth: Any = Any(),
    @SerializedName("email")
    var email: Any = Any(),
    @SerializedName("IBAN")
    var iBAN: Any = Any(),
    @SerializedName("iban")
    var iban: Any = Any(),
    @SerializedName("idCountryIso3")
    var idCountryIso3: Any = Any(),
    @SerializedName("idDocumentNumber")
    var idDocumentNumber: Any = Any(),
    @SerializedName("idDocumentType")
    var idDocumentType: Any = Any(),
    @SerializedName("idNumber")
    var idNumber: Any = Any(),
    @SerializedName("idType")
    var idType: Any = Any(),
    @SerializedName("idValidFrom")
    var idValidFrom: Any = Any(),
    @SerializedName("idValidTo")
    var idValidTo: Any = Any(),
    @SerializedName("ifscCode")
    var ifscCode: Any = Any(),
    @SerializedName("institutionNumber")
    var institutionNumber: Any = Any(),
    @SerializedName("interacAccount")
    var interacAccount: Any = Any(),
    @SerializedName("legalType")
    var legalType: String = "",
    @SerializedName("orderId")
    var orderId: Any = Any(),
    @SerializedName("payinReference")
    var payinReference: Any = Any(),
    @SerializedName("phoneNumber")
    var phoneNumber: Any = Any(),
    @SerializedName("province")
    var province: Any = Any(),
    @SerializedName("pspReference")
    var pspReference: Any = Any(),
    @SerializedName("routingNumber")
    var routingNumber: Any = Any(),
    @SerializedName("russiaRegion")
    var russiaRegion: Any = Any(),
    @SerializedName("rut")
    var rut: Any = Any(),
    @SerializedName("sortCode")
    var sortCode: String = "",
    @SerializedName("swiftCode")
    var swiftCode: Any = Any(),
    @SerializedName("targetProfile")
    var targetProfile: Any = Any(),
    @SerializedName("taxId")
    var taxId: Any = Any(),
    @SerializedName("token")
    var token: Any = Any(),
    @SerializedName("transitNumber")
    var transitNumber: Any = Any()
)