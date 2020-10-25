package parkee.parkee.transferwiseapps.ui

data class TransferMoneyModel(
    var sourceCurrency: String = "",
    var targetCurrency: String = "",
    var sourceAmount: Double = 0.0,
    var targetAmount: Int = 0,
    var status: String = ""
)