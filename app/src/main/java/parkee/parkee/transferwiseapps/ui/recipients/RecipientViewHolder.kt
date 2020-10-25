package parkee.parkee.transferwiseapps.ui.recipients

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipient_item_layout.view.*
import parkee.parkee.transferwiseapps.ui.RecipientModel

class RecipientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(recipientModel: RecipientModel) {

        itemView.textViewRecipientName.text = recipientModel.name

        itemView.textViewRecipientCurrency.text = recipientModel.currency
    }
}