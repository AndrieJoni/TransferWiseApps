package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_transfer_money.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.LoadingDialog

class TransferMoneyActivity : AppCompatActivity() {

    private val transferMoneyViewModel: TransferMoneyViewModel by viewModel()

    private var loadingDialog = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_money)
        initView()
        initListener()
        initObserver()
    }

    override fun onBackPressed() {
        if (viewPagerTransferMoney.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPagerTransferMoney.currentItem = viewPagerTransferMoney.currentItem - 1
        }
    }

    private fun initView() {

        val transferMoneyPagerAdapter = TransferMoneyPagerAdapter(this)

        transferMoneyPagerAdapter.addFragment(ChooseAndConvertCurrencyFragment())
        transferMoneyPagerAdapter.addFragment(ChooseRecipientFragment())
        transferMoneyPagerAdapter.addFragment(ChooseTransferReasonFragment())
        transferMoneyPagerAdapter.addFragment(TransferConfirmationFragment())

        viewPagerTransferMoney.offscreenPageLimit = 4
        viewPagerTransferMoney.isUserInputEnabled = false
        viewPagerTransferMoney.adapter = transferMoneyPagerAdapter
    }

    private fun initObserver() {

        transferMoneyViewModel.goToPageEvent.observe(this, {
            viewPagerTransferMoney.currentItem = it
        })

        transferMoneyViewModel.goBackWithResult.observe(this, {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        })

        transferMoneyViewModel.loadingEvent.observe(this, Observer {
            if (it) loadingDialog.show(supportFragmentManager, "LoadingDialog")
            else loadingDialog.dismiss()
        })
    }

    private fun initListener() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val REQUEST_TRANSFER_MONEY = 12225
    }
}