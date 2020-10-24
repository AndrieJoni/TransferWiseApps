package parkee.parkee.transferwiseapps.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_choose_currency.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.ui.FullScreenDialog

class ChooseCurrencyDialog : FullScreenDialog() {

    private lateinit var chooseCurrencyViewModel: ChooseCurrencyViewModel
    private var chooseCurrencyAdapter = ChooseCurrencyAdapter(listOf())

    var onCurrencyDialogListener: OnCurrencyDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseCurrencyViewModel = getViewModel()
        initObserver()
        onClickListener()
        chooseCurrencyViewModel.getAvailableCurrency()
    }

    private fun initObserver() {
        chooseCurrencyViewModel.setCurrencyAvailableEvent.observe(viewLifecycleOwner, {
            setCurrency(it)
        })
    }

    private fun onClickListener() {
        chooseCurrencyAdapter.onCurrencyAdapterListener =
            ChooseCurrencyAdapter.OnCurrencyAdapterListener {
                onCurrencyDialogListener?.onCurrencyItemClicked(it)
                dismiss()
            }
    }

    private fun setCurrency(data: List<CurrencyModel>) {

        chooseCurrencyAdapter.data = data

        rvChooseCurrency.adapter = chooseCurrencyAdapter
    }

    fun interface OnCurrencyDialogListener {
        fun onCurrencyItemClicked(currencyModel: CurrencyModel)
    }
}