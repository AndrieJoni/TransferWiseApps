import com.google.gson.annotations.SerializedName
import parkee.parkee.transferwiseapps.network.recipient.FieldDto

data class FormRequirmentsDto(
    @SerializedName("fields")
    var fields: List<FieldDto> = listOf(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("usageInfo")
    var usageInfo: Any = Any()
)
