package parkee.parkee.transferwiseapps.domain

import parkee.parkee.transferwiseapps.data.network.recipient.FieldDto
import parkee.parkee.transferwiseapps.data.network.recipient.FormRequirementsDto
import parkee.parkee.transferwiseapps.data.network.recipient.RecipientDto
import parkee.parkee.transferwiseapps.data.network.recipient.ValuesAllowedDto
import parkee.parkee.transferwiseapps.uiModel.FieldRequirementsModel
import parkee.parkee.transferwiseapps.uiModel.FieldSelectValuesRequirementsModel
import parkee.parkee.transferwiseapps.uiModel.RecipientBankDetailsModel
import parkee.parkee.transferwiseapps.uiModel.RecipientModel

fun Collection<RecipientDto>.mapToRecipientModel(): List<RecipientModel> {

    return map {
        RecipientModel(
            it.id,
            it.accountHolderName,
            it.currency
        )
    }
}

fun Collection<FieldDto>.mapToFieldRequirementsModel(): List<FieldRequirementsModel> {

    return map {

        if (it.group[0].valuesAllowed != null) {

            FieldRequirementsModel(
                it.group[0].name,
                it.group[0].key,
                it.group[0].type,
                it.group[0].valuesAllowed?.mapToFieldSelectValuesRequirementsModel(),
                ""
            )

        } else {

            FieldRequirementsModel(
                it.group[0].name,
                it.group[0].key,
                it.group[0].type,
                listOf(),
                ""
            )
        }
    }
}

fun Collection<ValuesAllowedDto>.mapToFieldSelectValuesRequirementsModel(): List<FieldSelectValuesRequirementsModel> {

    return map {
        FieldSelectValuesRequirementsModel(
            it.key,
            it.name
        )
    }
}

fun Collection<FormRequirementsDto>.mapToRecipientBankDetailsModel(): List<RecipientBankDetailsModel> {

    return map {
        RecipientBankDetailsModel(
            it.title,
            it.fields.mapToFieldRequirementsModel(),
            it.type
        )
    }
}
