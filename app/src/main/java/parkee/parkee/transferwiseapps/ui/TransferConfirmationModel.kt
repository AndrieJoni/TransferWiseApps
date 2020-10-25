package parkee.parkee.transferwiseapps.ui

data class TransferConfirmationModel(
    var sourceAmount: Int,
    var targetAmount:Int,
    var transferFee: Double,
    var guaranteeRate: Double,
    var sourceCurrency: CurrencyModel?,
    var targetCurrency: CurrencyModel?,
    var arriveTime : String,
    var recipient: RecipientModel?
)