package parkee.parkee.transferwiseapps.data.network.user

import com.google.gson.annotations.SerializedName


data class UserDto(
    @SerializedName("active")
    var active: Boolean = false,
    @SerializedName("details")
    var userDetailsDto: UserDetailsDto = UserDetailsDto(),
    @SerializedName("email")
    var email: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = ""
)

