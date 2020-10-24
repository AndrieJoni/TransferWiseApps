package parkee.parkee.transferwiseapps.network.quote
import com.google.gson.annotations.SerializedName


data class CreateQuoteResponseDto(
    @SerializedName("allowedProfileTypes")
    var allowedProfileTypes: List<String> = listOf(),
    @SerializedName("createdByUserId")
    var createdByUserId: Int = 0,
    @SerializedName("createdTime")
    var createdTime: String = "",
    @SerializedName("deliveryEstimate")
    var deliveryEstimate: String = "",
    @SerializedName("fee")
    var fee: Double = 0.0,
    @SerializedName("feeDetails")
    var feeDetails: FeeDetails = FeeDetails(),
    @SerializedName("guaranteedTargetAmount")
    var guaranteedTargetAmount: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("ofSourceAmount")
    var ofSourceAmount: Boolean = false,
    @SerializedName("profile")
    var profile: Int = 0,
    @SerializedName("rate")
    var rate: Double = 0.0,
    @SerializedName("rateType")
    var rateType: String = "",
    @SerializedName("source")
    var source: String = "",
    @SerializedName("sourceAmount")
    var sourceAmount: Double = 0.0,
    @SerializedName("target")
    var target: String = "",
    @SerializedName("targetAmount")
    var targetAmount: Double = 0.0,
    @SerializedName("type")
    var type: String = ""
)

data class FeeDetails(
    @SerializedName("discount")
    var discount: Double = 0.0,
    @SerializedName("partner")
    var partner: Double = 0.0,
    @SerializedName("payIn")
    var payIn: Double = 0.0,
    @SerializedName("transferwise")
    var transferwise: Double = 0.0
)