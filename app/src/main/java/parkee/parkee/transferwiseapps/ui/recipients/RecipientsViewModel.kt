package parkee.parkee.transferwiseapps.ui.recipients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.domain.mapToListCurrencyModel
import parkee.parkee.transferwiseapps.domain.mapToRecipientBankDetailsModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.CurrencyDto
import parkee.parkee.transferwiseapps.network.recipient.FormRequirementsDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.RecipientRepository
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.ui.FieldRequirementsModel
import parkee.parkee.transferwiseapps.ui.RecipientBankDetailsModel
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class RecipientsViewModel(
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val recipientRepository: RecipientRepository
) : ViewModel() {

    var setCurrencyAvailableEvent = SingleLiveEvent<List<CurrencyModel>>()
    var setRecipientBankDetailsEvent = SingleLiveEvent<List<RecipientBankDetailsModel>>()
    var showFieldRequirementsEvent = SingleLiveEvent<List<FieldRequirementsModel>>()

    fun getAvailableCurrency() {

        viewModelScope.launch {

            try {

                var currencyDto: List<CurrencyDto>? = null

                withContext(Dispatchers.IO) {
                    currencyDto = borderlessAccountsRepository.getAvailableCurrency()
                }

                if (currencyDto != null) {
                    setCurrencyAvailableEvent.value = currencyDto!!.mapToListCurrencyModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getFormRequirements(currency: String) {

        viewModelScope.launch {

            try {

                var formRequirmentsDto: List<FormRequirementsDto>? = null

                withContext(Dispatchers.IO) {
                    formRequirmentsDto = recipientRepository.getFormRequirments(
                        "IDR", currency
                    )
                }

                if (formRequirmentsDto != null) {
                    setRecipientBankDetailsEvent.value =
                        formRequirmentsDto!!.mapToRecipientBankDetailsModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun currencySelected(data: CurrencyModel) {
        getFormRequirements(data.currencyName)
    }

    fun recipientBankDetailsSelected(data: RecipientBankDetailsModel) {
        showFieldRequirementsEvent.value = data.fields.subList(1, data.fields.size)
    }
}