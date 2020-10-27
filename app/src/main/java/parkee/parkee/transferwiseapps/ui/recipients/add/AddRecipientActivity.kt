package parkee.parkee.transferwiseapps.ui.recipients.add

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_add_recipients.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.LoadingDialog
import parkee.parkee.transferwiseapps.ui.currency.ChooseCurrencyDialog
import parkee.parkee.transferwiseapps.uiModel.RecipientBankDetailsModel

class AddRecipientActivity : AppCompatActivity() {

    private val addRecipientViewModel: AddRecipientViewModel by viewModel()

    private val dynamicFieldAdapter = DynamicFieldAdapter(listOf())

    private var chooseCurrencyDialog = ChooseCurrencyDialog()
    private var loadingDialog = LoadingDialog()

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

        toolbar.setNavigationOnClickListener {
            finish()
        }

        textInputEditTextFirstName.addTextChangedListener {
            addRecipientViewModel.handleFirstNameTextWatcher(it.toString())
        }

        textInputEditTextLastName.addTextChangedListener {
            addRecipientViewModel.handleLastNameTextWather(it.toString())
        }

        textInputEditTextCurrency.addTextChangedListener {
            addRecipientViewModel.handleCurrencyTextWatcher(it.toString())
        }

        textInputEditTextCurrency.setOnClickListener {
            chooseCurrencyDialog.show(supportFragmentManager, "CurrencyDialog")
        }

        chooseCurrencyDialog.onCurrencyDialogListener =
            ChooseCurrencyDialog.OnCurrencyDialogListener {
                textInputEditTextCurrency.setText(it.currencyName)
                addRecipientViewModel.currencySelected(it)
            }

        spinnerRecipientsBankDetails.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    addRecipientViewModel.recipientBankDetailsSelected(
                        parent?.getItemAtPosition(position) as RecipientBankDetailsModel
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        btnAddRecipients.setOnClickListener {
            addRecipientViewModel.addRecipient(
                textInputEditTextFirstName.text.toString(),
                textInputEditTextLastName.text.toString(),
                spinnerRecipientType.selectedItem as String,
                spinnerRecipientsBankDetails.selectedItem as RecipientBankDetailsModel?,
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

        addRecipientViewModel.setRecipientBankDetailsEvent.observe(this, {
            setSpinnerRecipientBankDetails(it)
        })

        addRecipientViewModel.showFieldRequirementsEvent.observe(this, {

            dynamicFieldAdapter.data = it
            rvAddRecipientField.adapter = dynamicFieldAdapter
        })

        addRecipientViewModel.currencyErrorEvent.observe(this, {

            textInputLayoutTextCurrency.isErrorEnabled = it

            if (it) textInputLayoutTextCurrency.error = getString(R.string.error_choose_currency)
        })

        addRecipientViewModel.firstNameErrorEvent.observe(this, {

            textInputLayoutTextFirstName.isErrorEnabled = it

            if (it) textInputLayoutTextFirstName.error = getString(R.string.error_must_be_filled)
        })

        addRecipientViewModel.lastNameErrorEvent.observe(this, {

            textInputLayoutTextLastName.isErrorEnabled = false

            if (it) textInputLayoutTextLastName.error = getString(R.string.error_must_be_filled)
        })

        addRecipientViewModel.clearErrorEvent.observe(this, Observer {

            textInputLayoutTextCurrency.error = ""
            textInputLayoutTextFirstName.error = ""
            textInputLayoutTextLastName.error = ""

            textInputLayoutTextCurrency.isErrorEnabled = false
            textInputLayoutTextFirstName.isErrorEnabled = false
            textInputLayoutTextLastName.isErrorEnabled = false
        })

        addRecipientViewModel.goBackWithResultEvent.observe(this, Observer {
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        })

        addRecipientViewModel.loadingEvent.observe(this, Observer {
            if (it) loadingDialog.show(supportFragmentManager, "LoadingDialog")
            else loadingDialog.dismiss()
        })
    }

    companion object {
        const val REQUEST_ADD_RECIPIENT = 12345
    }
}