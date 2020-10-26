package parkee.parkee.transferwiseapps.uiModel

data class RecipientBankDetailsModel(
    val recipientBankDetailsName: String,
    val fields: List<FieldRequirementsModel>,
    val fieldsType: String
) {
    override fun toString(): String {
        return recipientBankDetailsName
    }
}