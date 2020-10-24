package parkee.parkee.transferwiseapps.ui.currency

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_item_layout.view.*
import parkee.parkee.transferwiseapps.ui.CurrencyModel

class ChooseCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(currencyModel: CurrencyModel) {
        itemView.textViewCurrencyName.text = currencyModel.currencyName
    }
}