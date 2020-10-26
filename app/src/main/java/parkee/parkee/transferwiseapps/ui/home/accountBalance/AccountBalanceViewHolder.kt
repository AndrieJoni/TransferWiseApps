package parkee.parkee.transferwiseapps.ui.home.accountBalance

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.account_balance_item_layout.view.*
import parkee.parkee.transferwiseapps.uiModel.AccountBalanceModel
import parkee.parkee.transferwiseapps.utils.FormatCurrency

class AccountBalanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(accontBalanceModel: AccountBalanceModel) {

        itemView.textViewBalance.text = FormatCurrency.convert(accontBalanceModel.balance.toLong())

        itemView.textViewCurrencyName.text = accontBalanceModel.currencyName
    }
}