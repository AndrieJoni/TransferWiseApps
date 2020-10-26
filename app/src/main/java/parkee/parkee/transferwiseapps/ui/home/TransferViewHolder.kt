package parkee.parkee.transferwiseapps.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transfer_item_layout.view.*
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel
import parkee.parkee.transferwiseapps.utils.FormatCurrency

class TransferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(transferMoneyModel: TransferMoneyModel) {

        itemView.textViewTransferTitle.text = String.format(
            "%s to %s",
            transferMoneyModel.sourceCurrency,
            transferMoneyModel.targetCurrency
        )

        itemView.textViewTransferSourceAmount.text = String.format(
            "%s %s",
            FormatCurrency.convert(transferMoneyModel.sourceAmount.toLong()),
            transferMoneyModel.sourceCurrency
        )

        itemView.textViewTransferTargetAmount.text = String.format(
            "%s %s",
            FormatCurrency.convert(transferMoneyModel.targetAmount.toLong()),
            transferMoneyModel.targetCurrency
        )

        itemView.textViewTransferStatus.text = transferMoneyModel.status
    }
}