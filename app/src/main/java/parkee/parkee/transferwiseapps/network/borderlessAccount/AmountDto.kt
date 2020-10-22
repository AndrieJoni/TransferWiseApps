package parkee.parkee.transferwiseapps.network.borderlessAccount

import com.google.gson.annotations.SerializedName

data class AmountDto(
    @SerializedName("currency")
    var currency: String = "",
    @SerializedName("value")
    var value: Int = 0
)