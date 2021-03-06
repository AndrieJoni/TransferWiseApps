package parkee.parkee.transferwiseapps.ui.transferMoney.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_choose_transfer_reason.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.transferMoney.TransferMoneyViewModel

class ChooseTransferReasonFragment : Fragment() {

    private val transferMoneyViewModel: TransferMoneyViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_transfer_reason, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinner()
        btnConfirmReason.setOnClickListener {
            transferMoneyViewModel.reasonConfirmed()
        }
    }

    private fun setSpinner() {

        val reasonTypes =

            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayListOf("Salary Payment", "Send money to familiy")
            )

        spinnerReasonType.adapter = reasonTypes
    }
}