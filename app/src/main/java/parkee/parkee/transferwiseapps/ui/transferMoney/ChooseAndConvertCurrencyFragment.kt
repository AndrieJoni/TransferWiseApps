package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_choose_and_convert_currency.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.currency.ChooseCurrencyDialog

class ChooseAndConvertCurrencyFragment : Fragment() {

    private val transferMoneyViewModel: TransferMoneyViewModel by sharedViewModel()

    private var currencySourceDialog = ChooseCurrencyDialog()
    private var currencyTargetDialog = ChooseCurrencyDialog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_and_convert_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        onClickListener()
    }

    private fun initView() {
        textViewFeeTransfer.text = String.format("Fee : %s", "0")
    }

    private fun initObserver() {

        transferMoneyViewModel.setFeeAmount.observe(viewLifecycleOwner, {
            textViewFeeTransfer.text = String.format("Fee : %s", it.toString())
        })

        transferMoneyViewModel.setTargetAmount.observe(viewLifecycleOwner, {
            textInputEditTextTargetAmount.setText(it.toString())
        })
    }

    private fun onClickListener() {

        textInputEditTextSourceCurrency.setOnClickListener {
            currencySourceDialog.show(childFragmentManager, "SourceCurrencyDialog")
        }

        textInputEditTextTargetCurrency.setOnClickListener {
            currencyTargetDialog.show(childFragmentManager, "TargetCurrencyDialog")
        }

        textInputEditTextSourceAmount.addTextChangedListener {
            transferMoneyViewModel.handleSourceTextWatcher(it.toString())
        }

        currencySourceDialog.onCurrencyDialogListener =
            ChooseCurrencyDialog.OnCurrencyDialogListener {
                textInputEditTextSourceCurrency.setText(it.currencyName)
                transferMoneyViewModel.sourceCurrencyChange(it)
            }

        currencyTargetDialog.onCurrencyDialogListener =
            ChooseCurrencyDialog.OnCurrencyDialogListener {
                textInputEditTextTargetCurrency.setText(it.currencyName)
                transferMoneyViewModel.targetCurrencyChange(it)
            }
    }
}