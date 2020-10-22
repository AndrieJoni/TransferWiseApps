package parkee.parkee.transferwiseapps.network.recipient

import com.google.gson.annotations.SerializedName

data class GroupDto(
    @SerializedName("displayFormat")
    var displayFormat: Any = Any(),
    @SerializedName("example")
    var example: String = "",
    @SerializedName("key")
    var key: String = "",
    @SerializedName("maxLength")
    var maxLength: Any = Any(),
    @SerializedName("minLength")
    var minLength: Any = Any(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("refreshRequirementsOnChange")
    var refreshRequirementsOnChange: Boolean = false,
    @SerializedName("required")
    var required: Boolean = false,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("validationAsync")
    var validationAsync: Any = Any(),
    @SerializedName("validationRegexp")
    var validationRegexp: Any = Any(),
    @SerializedName("valuesAllowed")
    var valuesAllowed: List<ValuesAllowedDto> = listOf()
)