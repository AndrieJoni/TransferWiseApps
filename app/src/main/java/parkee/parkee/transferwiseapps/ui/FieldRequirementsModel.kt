package parkee.parkee.transferwiseapps.ui

data class FieldRequirementsModel(
    val fieldName: String,
    val fieldType: String,
    val selectValues: List<FieldSelectValuesRequirementsModel>?,
    var selectedValues : String,
)