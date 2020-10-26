package parkee.parkee.transferwiseapps.uiModel

data class FieldSelectValuesRequirementsModel(val key: String, val keyName: String) {
    override fun toString(): String {
        return keyName
    }
}