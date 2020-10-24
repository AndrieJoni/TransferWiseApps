package parkee.parkee.transferwiseapps.ui.recipients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_add_recipients.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.ui.RecipientBankDetailsModel

class AddRecipientDialog : DialogFragment() {

    private val recipientsViewModel: RecipientsViewModel by sharedViewModel()

    private val dynamicFieldAdapter = DynamicFieldAdapter(listOf())

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
        initView()
        initListener()
        initObserver()
        recipientsViewModel.getAvailableCurrency()
    }

    private fun initView() {
        setSpinnerRecipientType()
    }

    private fun initListener() {

        spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                recipientsViewModel.currencySelected(
                    parent?.getItemAtPosition(position) as CurrencyModel
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerRecipientsBankDetails.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    recipientsViewModel.recipientBankDetailsSelected(
                        parent?.getItemAtPosition(position) as RecipientBankDetailsModel
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        btnAddRecipients.setOnClickListener {
            recipientsViewModel.addRecipient(
                textInputEditTextFirstName.text.toString(),
                textInputEditTextLastName.text.toString(),
                spinnerRecipientType.selectedItem as String,
                spinnerCurrency.selectedItem as CurrencyModel,
                spinnerRecipientsBankDetails.selectedItem as RecipientBankDetailsModel,
                dynamicFieldAdapter.data
            )
        }
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

    private fun setSpinnerRecipientBankDetails(data: List<RecipientBankDetailsModel>) {

        val recipientsBankDetails =

            context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.support_simple_spinner_dropdown_item,
                    data
                )
            }

        spinnerRecipientsBankDetails.adapter = recipientsBankDetails
    }

    private fun setSpinnerRecipientType() {

        val recipientTypes =

            context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.support_simple_spinner_dropdown_item,
                    arrayListOf("PRIVATE", "BUSINESS")
                )
            }

        spinnerRecipientType.adapter = recipientTypes
    }

    private fun initObserver() {

        recipientsViewModel.setCurrencyAvailableEvent.observe(viewLifecycleOwner, {
            setSpinnerCurrency(it)
        })

        recipientsViewModel.setRecipientBankDetailsEvent.observe(viewLifecycleOwner, {
            setSpinnerRecipientBankDetails(it)
        })

        recipientsViewModel.showFieldRequirementsEvent.observe(viewLifecycleOwner, {

            dynamicFieldAdapter.data = it
            rvAddRecipientField.adapter = dynamicFieldAdapter
        })
    }
}