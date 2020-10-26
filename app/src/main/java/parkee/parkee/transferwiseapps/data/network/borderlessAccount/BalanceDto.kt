package parkee.parkee.transferwiseapps.data.network.borderlessAccount

import com.google.gson.annotations.SerializedName

data class BalanceDto(
    @SerializedName("amount")
    var amountDto: AmountDto = AmountDto(),
    @SerializedName("balanceType")
    var balanceType: String = "",
    @SerializedName("bankDetails")
    var bankDetails: Any = Any(),
    @SerializedName("currency")
    var currency: String = "",
    @SerializedName("reservedAmount")
    var reservedAmountDto: ReservedAmountDto = ReservedAmountDto()
)

