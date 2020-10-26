package parkee.parkee.transferwiseapps.domain

import parkee.parkee.transferwiseapps.data.network.borderlessAccount.BalanceDto
import parkee.parkee.transferwiseapps.data.network.borderlessAccount.CurrencyDto
import parkee.parkee.transferwiseapps.uiModel.AccountBalanceModel
import parkee.parkee.transferwiseapps.uiModel.CurrencyModel


fun Collection<BalanceDto>.mapToListAccountBalanceModel(): List<AccountBalanceModel> {
    return map {
        it.mapToListAccountBalanceModel()
    }
}

fun BalanceDto.mapToListAccountBalanceModel(): AccountBalanceModel {
    return AccountBalanceModel(
        currencyName = currency,
        balance = amountDto.value
    )
}

fun Collection<CurrencyDto>.mapToListCurrencyModel(): List<CurrencyModel> {
    return map {
        it.mapToCurrencyModel()
    }
}

fun CurrencyDto.mapToCurrencyModel(): CurrencyModel {
    return CurrencyModel(currencyName = code)
}