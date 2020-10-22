package parkee.parkee.transferwiseapps.ui.recipients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_add_recipients.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.CurrencyModel

class AddRecipientDialog : DialogFragment() {

    private val recipientsViewModel: RecipientsViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransferWise_DialogStyle)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return requireActivity().layoutInflater.inflate(
            R.layout.dialog_add_recipients,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        recipientsViewModel.getAvailableCurrency()
    }

    private fun setSpinnerCurrency(data: List<CurrencyModel>) {

        val currencyAdapter =
            context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.support_simple_spinner_dropdown_item,
                    data
                )
            }

        spinnerCurrency.adapter = currencyAdapter
    }

    private fun initObserver() {

        recipientsViewModel.setCurrencyAvailableEvent.observe(this, {
            setSpinnerCurrency(it)
        })
    }
}