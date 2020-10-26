package parkee.parkee.transferwiseapps.ui.home.accountBalance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.uiModel.AccountBalanceModel

class AccountBalanceAdapter(private var data: List<AccountBalanceModel>) :
    RecyclerView.Adapter<AccountBalanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountBalanceViewHolder {
        return AccountBalanceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.account_balance_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountBalanceViewHolder, position: Int) {
        holder.renderView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}