package parkee.parkee.transferwiseapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import parkee.parkee.transferwiseapps.repository.UserRepository

class MainActivityViewModel(private val userRepository: UserRepository) :ViewModel() {

    fun tes() {
        viewModelScope.launch {
            userRepository.getCurrentUser()
        }
    }
}