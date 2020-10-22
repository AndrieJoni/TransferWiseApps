package parkee.parkee.transferwiseapps.network.recipient

import com.google.gson.annotations.SerializedName


data class ValuesAllowedDto(
    @SerializedName("key")
    var key: String = "",
    @SerializedName("name")
    var name: String = ""
)