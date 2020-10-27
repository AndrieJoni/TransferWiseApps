package parkee.parkee.transferwiseapps.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.data.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.data.network.transfer.CreateTransferResponseDto
import parkee.parkee.transferwiseapps.data.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.data.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.data.repository.TransferRepository
import parkee.parkee.transferwiseapps.data.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.data.repository.UserRepository
import parkee.parkee.transferwiseapps.domain.mapToListAccountBalanceModel
import parkee.parkee.transferwiseapps.domain.mapToTransferMoneyModel
import parkee.parkee.transferwiseapps.uiModel.AccountBalanceModel
import parkee.parkee.transferwiseapps.uiModel.TransferMoneyModel

class HomeViewModel(
    private val userRepository: UserRepository,
    private val userProfilesRepository: UserProfilesRepository,
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val transferRepository: TransferRepository
) : ViewModel(), LifecycleObserver {

    var showAccountBalanceEvent = MutableLiveData<List<AccountBalanceModel>>()
    var showTransferList = MutableLiveData<List<TransferMoneyModel>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        getAccountBalance()
        getAllTransfer()
    }

    private fun getAccountBalance() {

        viewModelScope.launch {

            try {

                var userProfilesPersonalDto: List<UserProfilesPersonalDto> = listOf()

                withContext(Dispatchers.IO) {
                    userProfilesPersonalDto = userProfilesRepository.getUserProfiles()
                }

                if (!userProfilesPersonalDto.isNullOrEmpty()) {

                    var accountBalanceDto: List<AccountBalanceDto>? = listOf()

                    withContext(Dispatchers.IO) {
                        accountBalanceDto =
                            borderlessAccountsRepository
                                .getAccountBalance(userProfilesPersonalDto[0].id.toString())
                    }

                    showAccountBalanceEvent.value =
                        accountBalanceDto?.get(0)?.balanceDtos?.mapToListAccountBalanceModel()

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAllTransfer() {

        viewModelScope.launch {

            try {

                var transferList: List<CreateTransferResponseDto>? = null

                withContext(Dispatchers.IO) {
                    transferList = transferRepository.getAllTransfer()
                }

                if (transferList != null) {
                    showTransferList.value = transferList?.mapToTransferMoneyModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}