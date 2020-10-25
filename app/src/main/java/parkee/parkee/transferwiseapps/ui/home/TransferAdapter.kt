package parkee.parkee.transferwiseapps.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel

class TransferAdapter(var data: List<TransferMoneyModel>) :
    RecyclerView.Adapter<TransferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        return TransferViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transfer_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        holder.renderView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}