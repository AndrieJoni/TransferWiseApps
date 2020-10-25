package parkee.parkee.transferwiseapps.ui.recipients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipient_item_layout.view.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.RecipientModel

class RecipientAdapter(var data: List<RecipientModel>) :
    RecyclerView.Adapter<RecipientViewHolder>() {

    var onRecipientAdapterListener: OnRecipientAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipientViewHolder {
        return RecipientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recipient_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipientViewHolder, position: Int) {

        holder.renderView(data[position])

        holder.itemView.llRecipient.setOnClickListener {
            onRecipientAdapterListener?.onRecipientClicked(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun interface OnRecipientAdapterListener {
        fun onRecipientClicked(recipientModel: RecipientModel)
    }
}