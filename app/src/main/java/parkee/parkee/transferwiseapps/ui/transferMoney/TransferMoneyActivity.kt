package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transfer_money.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R

class TransferMoneyActivity : AppCompatActivity() {

    private val transferMoneyViewModel: TransferMoneyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_money)
        initView()
        initObserver()
    }

    private fun initView() {

        val transferMoneyPagerAdapter = TransferMoneyPagerAdapter(this)

        transferMoneyPagerAdapter.addFragment(ChooseAndConvertCurrencyFragment())
        transferMoneyPagerAdapter.addFragment(ChooseRecipientFragment())
        transferMoneyPagerAdapter.addFragment(ChooseTransferReasonFragment())
        transferMoneyPagerAdapter.addFragment(TransferConfirmationFragment())

        viewPagerTransferMoney.offscreenPageLimit = 4
        viewPagerTransferMoney.isUserInputEnabled = true
        viewPagerTransferMoney.adapter = transferMoneyPagerAdapter
    }

    private fun initObserver() {
        transferMoneyViewModel.goToPageEvent.observe(this, {
            viewPagerTransferMoney.currentItem = it
        })
    }
}