package parkee.parkee.transferwiseapps.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_item_layout.view.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.CurrencyModel

class ChooseCurrencyAdapter(var data: List<CurrencyModel>) :
    RecyclerView.Adapter<ChooseCurrencyViewHolder>() {

    var onCurrencyAdapterListener: OnCurrencyAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCurrencyViewHolder {
        return ChooseCurrencyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChooseCurrencyViewHolder, position: Int) {

        holder.renderView(data[position])

        holder.itemView.llCurrency.setOnClickListener {
            onCurrencyAdapterListener?.onCurrencyCliked(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun interface OnCurrencyAdapterListener {
        fun onCurrencyCliked(currencyModel: CurrencyModel)
    }
}