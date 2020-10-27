package parkee.parkee.transferwiseapps.data.network.borderlessAccount

import com.google.gson.annotations.SerializedName


data class CurrencyDto(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("hasBankDetails")
    var hasBankDetails: Boolean = false,
    @SerializedName("payInAllowed")
    var payInAllowed: Boolean = false,
    @SerializedName("sampleBankDetails")
    var sampleBankDetails: Any = Any()
)