package parkee.parkee.transferwiseapps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import parkee.parkee.transferwiseapps.domain.mapToListAccountBalanceModel
import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import parkee.parkee.transferwiseapps.network.user.UserDto
import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesPersonalDto
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.repository.UserRepository
import parkee.parkee.transferwiseapps.ui.AccountBalanceModel

class HomeViewModel(
    private val userRepository: UserRepository,
    private val userProfilesRepository: UserProfilesRepository,
    private val borderlessAccountsRepository: BorderlessAccountsRepository
) : ViewModel() {

    var showAccountBalanceEvent = MutableLiveData<List<AccountBalanceModel>>()

    init {
        getAccountBalance()
    }

    private fun getAccountBalance() {

        viewModelScope.launch {

            try {

                var userDto: UserDto? = null

                withContext(Dispatchers.IO) {
                    userDto = userRepository.getCurrentUser()
                }

                if (userDto != null) {

                    var userProfilesPersonalDto: UserProfilesPersonalDto? = null

                    withContext(Dispatchers.IO) {
                        userProfilesPersonalDto = userProfilesRepository.getUserProfiles(
                            mapOf(
                                "type" to "personal",
                                "details" to mapOf(
                                    "firstName" to userDto!!.userDetailsDto.firstName,
                                    "lastName" to userDto!!.userDetailsDto.lastName,
                                    "dateOfBirth" to userDto!!.userDetailsDto.dateOfBirth,
                                    "phoneNumber" to userDto!!.userDetailsDto.phoneNumber
                                )
                            )
                        )
                    }

                    var accountBalanceDto: List<AccountBalanceDto>? = listOf()

                    withContext(Dispatchers.IO) {
                        accountBalanceDto =
                            borderlessAccountsRepository.getAccountBalance(userProfilesPersonalDto?.id.toString())
                    }

                    showAccountBalanceEvent.value =
                        accountBalanceDto?.get(0)?.balanceDtos?.mapToListAccountBalanceModel()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}