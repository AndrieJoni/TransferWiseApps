package parkee.parkee.transferwiseapps.ui.recipients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.data.network.recipient.RecipientDto
import parkee.parkee.transferwiseapps.data.repository.RecipientRepository
import parkee.parkee.transferwiseapps.domain.mapToRecipientModel
import parkee.parkee.transferwiseapps.uiModel.RecipientModel

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