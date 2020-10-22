package parkee.parkee.transferwiseapps.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.account_balance_item_layout.view.*
import parkee.parkee.transferwiseapps.ui.AccountBalanceModel

class AccountBalanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(accontBalanceModel: AccountBalanceModel) {

        itemView.textViewBalance.text = accontBalanceModel.balance.toString()

        itemView.textViewCurrencyName.text = accontBalanceModel.currencyName
    }
}