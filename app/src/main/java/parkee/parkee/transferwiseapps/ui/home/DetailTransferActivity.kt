package parkee.parkee.transferwiseapps.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detail_transfer.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import parkee.parkee.transferwiseapps.R

class DetailTransferActivity : AppCompatActivity() {

    private val detailTransferViewModel: DetailTransferViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(TRANSFER_MONEY_DATA))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transfer)
        initObserver()
        initListener()
    }

    private fun initObserver() {

        detailTransferViewModel.setFeeData.observe(this, {
            textViewFeeDetailTransfer.text = it.toString()
        })

        detailTransferViewModel.setExchangeRateData.observe(this, {
            textViewExchangeRateDetailTransfer.text = it.toString()
        })

        detailTransferViewModel.setTransferDetail.observe(this, {
            textViewTitleDetailTransfer.text =
                String.format("%s to %s", it.sourceCurrency, it.targetCurrency)
        })

        detailTransferViewModel.setArrivedTime.observe(this, Observer {
            textViewArrivedTimeDetailTransfer.text = ZonedDateTime.parse(it).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
        })
    }

    private fun initListener() {

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        const val TRANSFER_MONEY_DATA = "TransferMoneyData"
    }
}