package parkee.parkee.transferwiseapps.uiModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransferMoneyModel(
    var id: Int = 0,
    var quoteId: Int = 0,
    var sourceCurrency: String = "",
    var targetCurrency: String = "",
    var sourceAmount: Double = 0.0,
    var targetAmount: Int = 0,
    var status: String = ""
) : Parcelable