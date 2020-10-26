package parkee.parkee.transferwiseapps.data.network.recipient

import com.google.gson.annotations.SerializedName

data class FormRequirementsDto(
    @SerializedName("fields")
    var fields: List<FieldDto> = listOf(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("usageInfo")
    var usageInfo: Any = Any()
)
