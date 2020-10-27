package parkee.parkee.transferwiseapps.ui.login

import androidx.lifecycle.ViewModel
import parkee.parkee.transferwiseapps.session.SessionManager
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class LoginViewModel(sessionManager: SessionManager) : ViewModel() {

    var setPasswordEmptyErrorEvent = SingleLiveEvent<Boolean>()
    var clearEmailErrorEvent = SingleLiveEvent<Any>()
    var setEmailEmptyErrorEvent = SingleLiveEvent<Boolean>()
    var setEmailFormatErrorEvent = SingleLiveEvent<Boolean>()
    var setButtonLoginEnabled = SingleLiveEvent<Boolean>()

    var goToVerificationOtpEvent = SingleLiveEvent<Any>()
    var goToMain = SingleLiveEvent<Any>()

    private var isPasswordValid = false
    private var isEmailValid = false

    init {
        if (sessionManager.isLogin) {
            goToMain.call()
        }
    }

    fun handleEmailTextWatcher(email: String) {

        isEmailValid = if (email.isEmpty()) {
            setEmailEmptyErrorEvent.value = true
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            setEmailFormatErrorEvent.value = true

            false
        } else {
            clearEmailErrorEvent.call()
            true
        }

        checkEmailAndPassword()
    }

    fun handlePasswordTextWatcher(password: String) {

        isPasswordValid = if (password.isEmpty()) {
            setPasswordEmptyErrorEvent.value = true
            false
        } else {
            setPasswordEmptyErrorEvent.value = false
            true
        }

        checkEmailAndPassword()
    }

    private fun checkEmailAndPassword() {
        setButtonLoginEnabled.value = isEmailValid && isPasswordValid
    }

    fun loginClicked() {
        goToVerificationOtpEvent.call()
    }
}