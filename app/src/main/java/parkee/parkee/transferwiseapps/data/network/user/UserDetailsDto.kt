package parkee.parkee.transferwiseapps.data.network.user

import com.google.gson.annotations.SerializedName

data class UserDetailsDto(
    @SerializedName("address")
    var userAddressDto: UserAddressDto = UserAddressDto(),
    @SerializedName("avatar")
    var avatar: String = "",
    @SerializedName("dateOfBirth")
    var dateOfBirth: String = "",
    @SerializedName("firstName")
    var firstName: String = "",
    @SerializedName("lastName")
    var lastName: String = "",
    @SerializedName("occupation")
    var occupation: String = "",
    @SerializedName("phoneNumber")
    var phoneNumber: String = "",
    @SerializedName("primaryAddress")
    var primaryAddress: Int = 0
)