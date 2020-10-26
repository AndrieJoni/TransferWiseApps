package parkee.parkee.transferwiseapps.data.network.user

import com.google.gson.annotations.SerializedName


data class UserAddressDto(
    @SerializedName("city")
    var city: String = "",
    @SerializedName("countryCode")
    var countryCode: String = "",
    @SerializedName("firstLine")
    var firstLine: String = "",
    @SerializedName("postCode")
    var postCode: String = "",
    @SerializedName("state")
    var state: String = ""
)