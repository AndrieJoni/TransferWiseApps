package parkee.parkee.transferwiseapps.data.network.userProfiles

import com.google.gson.annotations.SerializedName


data class UserProfilesPersonalDto(
    @SerializedName("details")
    var details: UserProfilesPersonalDetails = UserProfilesPersonalDetails(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("type")
    var type: String = ""
)