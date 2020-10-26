package parkee.parkee.transferwiseapps.ui.home

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.home.accountBalance.AccountBalanceAdapter
import parkee.parkee.transferwiseapps.ui.home.detailTransfer.DetailTransferActivity
import parkee.parkee.transferwiseapps.ui.home.listTransfer.TransferAdapter
import parkee.parkee.transferwiseapps.uiModel.TransferMoneyModel

class HomeFragment : Fragment(), TransferAdapter.OnTransferAdapterListener {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    override fun onTransferClicked(transferMoneyModel: TransferMoneyModel) {

        val intent = Intent(requireActivity(), DetailTransferActivity::class.java)
        intent.putExtra(DetailTransferActivity.TRANSFER_MONEY_DATA,transferMoneyModel)

        startActivity(intent)
    }

    private fun initView() {

        rvTransferList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        rvAccountBalance.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                outRect.top = resources
                    .getDimension(R.dimen.base16).toInt()
                outRect.bottom = resources
                    .getDimension(R.dimen.base16).toInt()
                outRect.left = (if (position == 0) resources
                    .getDimension(R.dimen.base16) else resources
                    .getDimension(R.dimen.base8)).toInt()
                outRect.right = (if (position == state.itemCount - 1) resources
                    .getDimension(R.dimen.base16) else resources
                    .getDimension(R.dimen.base8)).toInt()
            }
        })
    }

    private fun initObserver() {

        lifecycle.addObserver(homeViewModel)

        homeViewModel.showAccountBalanceEvent.observe(viewLifecycleOwner, {

            rvAccountBalance.adapter = AccountBalanceAdapter(it)
        })

        homeViewModel.showTransferList.observe(viewLifecycleOwner, {
            rvTransferList.adapter = TransferAdapter(it,this)
        })
    }
}