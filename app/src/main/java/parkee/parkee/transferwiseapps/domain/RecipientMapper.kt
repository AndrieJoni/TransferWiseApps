package parkee.parkee.transferwiseapps.domain

import parkee.parkee.transferwiseapps.network.recipient.FieldDto
import parkee.parkee.transferwiseapps.network.recipient.ValuesAllowedDto
import parkee.parkee.transferwiseapps.ui.FieldRequirmentsModel
import parkee.parkee.transferwiseapps.ui.FieldSelectValuesRequirmentsModel

fun Collection<FieldDto>.mapToFieldRequirmentsModel(): List<FieldRequirmentsModel> {

    return map {
        FieldRequirmentsModel(
            it.name,
            it.group[0].type,
            it.group[0].valuesAllowed.mapToFieldSelectValuesRequirmentsModel()
        )
    }
}

fun Collection<ValuesAllowedDto>.mapToFieldSelectValuesRequirmentsModel(): List<FieldSelectValuesRequirmentsModel> {

    return map {
        FieldSelectValuesRequirmentsModel(
            it.key,
            it.name
        )
    }
}
