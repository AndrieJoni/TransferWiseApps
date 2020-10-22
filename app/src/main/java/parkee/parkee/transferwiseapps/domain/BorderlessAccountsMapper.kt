package parkee.parkee.transferwiseapps.domain

import parkee.parkee.transferwiseapps.network.borderlessAccount.BalanceDto
import parkee.parkee.transferwiseapps.network.borderlessAccount.CurrencyDto
import parkee.parkee.transferwiseapps.ui.AccountBalanceModel
import parkee.parkee.transferwiseapps.ui.CurrencyModel


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