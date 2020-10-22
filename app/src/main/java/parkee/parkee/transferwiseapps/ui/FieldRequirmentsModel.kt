package parkee.parkee.transferwiseapps.ui

data class FieldRequirmentsModel(
    val fieldName: String,
    val fieldType: String,
    val selectValues: List<FieldSelectValuesRequirmentsModel>
)