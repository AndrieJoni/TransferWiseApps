package parkee.parkee.transferwiseapps.network.transfer
import com.google.gson.annotations.SerializedName

data class CreateTransferResponseDto(
    @SerializedName("business")
    var business: Int = 0,
    @SerializedName("created")
    var created: String = "",
    @SerializedName("customerTransactionId")
    var customerTransactionId: String = "",
    @SerializedName("details")
    var details: Details = Details(),
    @SerializedName("hasActiveIssues")
    var hasActiveIssues: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("quote")
    var quote: Int = 0,
    @SerializedName("rate")
    var rate: Double = 0.0,
    @SerializedName("reference")
    var reference: String = "",
    @SerializedName("sourceAccount")
    var sourceAccount: Any = Any(),
    @SerializedName("sourceCurrency")
    var sourceCurrency: String = "",
    @SerializedName("sourceValue")
    var sourceValue: Double = 0.0,
    @SerializedName("status")
    var status: String = "",
    @SerializedName("targetAccount")
    var targetAccount: Int = 0,
    @SerializedName("targetCurrency")
    var targetCurrency: String = "",
    @SerializedName("targetValue")
    var targetValue: Int = 0,
    @SerializedName("transferRequest")
    var transferRequest: Any = Any(),
    @SerializedName("user")
    var user: Int = 0
)

data class Details(
    @SerializedName("reference")
    var reference: String = ""
)