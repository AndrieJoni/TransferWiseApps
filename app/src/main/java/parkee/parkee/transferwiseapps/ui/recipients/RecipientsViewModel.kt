package parkee.parkee.transferwiseapps.ui.recipients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import parkee.parkee.transferwiseapps.domain.mapToRecipientBankDetailsModel
import parkee.parkee.transferwiseapps.domain.mapToRecipientModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.network.recipient.RecipientDto
import parkee.parkee.transferwiseapps.network.recipient.FormRequirementsDto
import parkee.parkee.transferwiseapps.network.recipient.ValidationRequirementsDto
import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.RecipientRepository
import parkee.parkee.transferwiseapps.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.ui.CurrencyModel
import parkee.parkee.transferwiseapps.ui.FieldRequirementsModel
import parkee.parkee.transferwiseapps.ui.RecipientBankDetailsModel
import parkee.parkee.transferwiseapps.ui.RecipientModel
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class RecipientsViewModel(
    private val recipientRepository: RecipientRepository,
) : ViewModel() {

    var setRecipientList = MutableLiveData<List<RecipientModel>>()

    fun getAllRecipient() {

        viewModelScope.launch {

            try {

                var recipientData: List<RecipientDto>? = null

                withContext(Dispatchers.IO) {
                    recipientData = recipientRepository.getAllRecipient()
                }

                if (recipientData != null) {
                    setRecipientList.value = recipientData?.mapToRecipientModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}