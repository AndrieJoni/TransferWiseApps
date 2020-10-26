package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_transfer_confirmation.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import parkee.parkee.transferwiseapps.R

class TransferConfirmationFragment : Fragment() {

    private val transferMoneyViewModel: TransferMoneyViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transfer_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        transferMoneyViewModel.showTransferConfirmationDetail()
    }

    private fun initObserver() {

        transferMoneyViewModel.setTransferConfirmationDetail.observe(viewLifecycleOwner, {

            textViewYouSendValue.text =
                String.format("%s %s", it.sourceAmount.toString(), it.sourceCurrency?.currencyName)

            textViewTotalFeeValue.text =
                String.format("%s %s", it.transferFee.toString(), it.sourceCurrency?.currencyName)

            textViewMoneyConvertValue.text =
                String.format(
                    "%s %s",
                    (it.sourceAmount - it.transferFee).toString(),
                    it.sourceCurrency?.currencyName
                )

            textViewGuaranteeRateValue.text = it.guaranteeRate.toString()

            textViewShouldArriveValue.text = ZonedDateTime.parse(it.arriveTime).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )

            textViewRecipientGetValue.text =
                String.format("%s %s", it.targetAmount, it.targetCurrency?.currencyName)

            textViewRecipientNameValue.text = it.recipient?.name
        })
    }

    private fun initListener() {
        btnTransfer.setOnClickListener {
            transferMoneyViewModel.buttonPayClicked()
        }
    }
}