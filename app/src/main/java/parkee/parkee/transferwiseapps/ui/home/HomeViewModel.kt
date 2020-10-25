package parkee.parkee.transferwiseapps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.domain.mapToListAccountBalanceModel
import parkee.parkee.transferwiseapps.domain.mapToTransferMoneyModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.network.transfer.CreateTransferResponseDto
import parkee.parkee.transferwiseapps.network.transfer.TransferRepository
import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.repository.UserRepository
import parkee.parkee.transferwiseapps.ui.AccountBalanceModel
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel

class HomeViewModel(
    private val userRepository: UserRepository,
    private val userProfilesRepository: UserProfilesRepository,
    private val borderlessAccountsRepository: BorderlessAccountsRepository,
    private val transferRepository: TransferRepository
) : ViewModel() {

    var showAccountBalanceEvent = MutableLiveData<List<AccountBalanceModel>>()
    var showTransferList = MutableLiveData<List<TransferMoneyModel>>()

    init {
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