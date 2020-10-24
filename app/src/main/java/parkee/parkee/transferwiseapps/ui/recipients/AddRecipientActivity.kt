package parkee.parkee.transferwiseapps.ui.recipients

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_recipients.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.RecipientBankDetailsModel
import parkee.parkee.transferwiseapps.ui.currency.ChooseCurrencyDialog

class AddRecipientActivity : AppCompatActivity() {

    private val recipientsViewModel: RecipientsViewModel by viewModel()

    private val dynamicFieldAdapter = DynamicFieldAdapter(listOf())

    private var chooseCurrencyDialog = ChooseCurrencyDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_add_recipients
        )
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        setSpinnerRecipientType()
    }

    private fun initListener() {

        textInputEditTextCurrency.setOnClickListener {
            chooseCurrencyDialog.show(supportFragmentManager, "CurrencyDialog")
        }

        chooseCurrencyDialog.onCurrencyDialogListener =
            ChooseCurrencyDialog.OnCurrencyDialogListener {
                textInputEditTextCurrency.setText(it.currencyName)
                recipientsViewModel.currencySelected(it)
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
                spinnerRecipientsBankDetails.selectedItem as RecipientBankDetailsModel,
                dynamicFieldAdapter.data
            )
        }
    }

    private fun setSpinnerRecipientBankDetails(data: List<RecipientBankDetailsModel>) {

        val recipientsBankDetails =

            ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                data
            )

        spinnerRecipientsBankDetails.adapter = recipientsBankDetails
    }

    private fun setSpinnerRecipientType() {

        val recipientTypes =

            ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                arrayListOf("PRIVATE", "BUSINESS")
            )

        spinnerRecipientType.adapter = recipientTypes
    }

    private fun initObserver() {

        recipientsViewModel.setRecipientBankDetailsEvent.observe(this, {
            setSpinnerRecipientBankDetails(it)
        })

        recipientsViewModel.showFieldRequirementsEvent.observe(this, {

            dynamicFieldAdapter.data = it
            rvAddRecipientField.adapter = dynamicFieldAdapter
        })
    }
}