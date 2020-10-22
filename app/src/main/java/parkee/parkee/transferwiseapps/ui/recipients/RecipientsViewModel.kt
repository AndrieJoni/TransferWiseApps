package parkee.parkee.transferwiseapps.ui.recipients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.domain.mapToListCurrencyModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.CurrencyDto
import parkee.parkee.transferwiseapps.repository.RecipientRepository
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.ui.CurrencyModel

class RecipientsViewModel(
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val recipientRepository: RecipientRepository
) : ViewModel() {

    var setCurrencyAvailableEvent = MutableLiveData<List<CurrencyModel>>()

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


    private fun getFormRequirements() {

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
}