package parkee.parkee.transferwiseapps.domain

import parkee.parkee.transferwiseapps.data.network.transfer.CreateTransferResponseDto
import parkee.parkee.transferwiseapps.uiModel.TransferMoneyModel

fun Collection<CreateTransferResponseDto>.mapToTransferMoneyModel() : List<TransferMoneyModel> {

    return map {
        TransferMoneyModel(
            id = it.id,
            quoteId = it.quote,
            sourceCurrency = it.sourceCurrency,
            targetCurrency = it.targetCurrency,
            sourceAmount = it.sourceValue,
            targetAmount = it.targetValue,
            status = it.status
        )
    }
}