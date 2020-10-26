package parkee.parkee.transferwiseapps.data.network.recipient

import com.google.gson.annotations.SerializedName

data class FieldDto(
    @SerializedName("group")
    var group: List<GroupDto> = listOf(),
    @SerializedName("name")
    var name: String = ""
)