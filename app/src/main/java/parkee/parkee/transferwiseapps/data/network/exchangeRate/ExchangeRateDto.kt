package parkee.parkee.transferwiseapps.data.network.exchangeRate
import com.google.gson.annotations.SerializedName

data class ExchangeRateDto(
    @SerializedName("rate")
    var rate: Double = 0.0,
    @SerializedName("source")
    var source: String = "",
    @SerializedName("target")
    var target: String = "",
    @SerializedName("time")
    var time: String = ""
)