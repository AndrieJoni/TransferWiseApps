package parkee.parkee.transferwiseapps.uiModel

data class FieldRequirementsModel(
    val fieldName: String,
    val fieldKey : String,
    val fieldType: String,
    val selectValues: List<FieldSelectValuesRequirementsModel>?,
    var selectedValues : String,
)