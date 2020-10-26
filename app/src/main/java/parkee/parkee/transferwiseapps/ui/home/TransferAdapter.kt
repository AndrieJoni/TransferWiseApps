package parkee.parkee.transferwiseapps.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transfer_item_layout.view.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel

class TransferAdapter(
    var data: List<TransferMoneyModel>,
    var onTransferAdapterListener: OnTransferAdapterListener
) :
    RecyclerView.Adapter<TransferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        return TransferViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transfer_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {

        holder.renderView(data[position])

        holder.itemView.llTransferList.setOnClickListener {
            onTransferAdapterListener.onTransferClicked(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun interface OnTransferAdapterListener {
        fun onTransferClicked(transferMoneyModel: TransferMoneyModel)
    }
}