package parkee.parkee.transferwiseapps.ui.verificationOtp

import androidx.lifecycle.ViewModel
import parkee.parkee.transferwiseapps.session.SessionManager
import parkee.parkee.transferwiseapps.utils.SingleLiveEvent

class VerificationOtpViewModel(private val sessionManager: SessionManager) : ViewModel() {

    var requestFocusDigit1Event = SingleLiveEvent<Any>()
    var requestFocusDigit2Event = SingleLiveEvent<Any>()
    var requestFocusDigit3Event = SingleLiveEvent<Any>()
    var requestFocusDigit4Event = SingleLiveEvent<Any>()
    var requestFocusDigit5Event = SingleLiveEvent<Any>()
    var requestFocusDigit6Event = SingleLiveEvent<Any>()

    var wrongOtpEvent = SingleLiveEvent<Any>()

    var goToMain = SingleLiveEvent<Any>()

    var digit1 = ""
    var digit2 = ""
    var digit3 = ""
    var digit4 = ""
    var digit5 = ""
    var digit6 = ""

    fun handleDigit1(digit1: String) {

        this.digit1 = digit1

        if (digit1.length == 1) {
            requestFocusDigit2Event.call()
        }
    }

    fun handleDigit2(digit2: String) {

        this.digit2 = digit2

        if (digit2.length == 1) {
            requestFocusDigit3Event.call()
        } else if (digit2.isEmpty()) {
            requestFocusDigit1Event.call()
        }
    }

    fun handleDigit3(digit3: String) {

        this.digit3 = digit3

        if (digit3.length == 1) {
            requestFocusDigit4Event.call()
        } else if (digit3.isEmpty()) {
            requestFocusDigit2Event.call()
        }
    }

    fun handleDigit4(digit4: String) {

        this.digit4 = digit4

        if (digit4.length == 1) {
            requestFocusDigit5Event.call()
        } else if (digit4.isEmpty()) {
            requestFocusDigit3Event.call()
        }
    }

    fun handleDigit5(digit5: String) {

        this.digit5 = digit5

        if (digit5.length == 1) {
            requestFocusDigit6Event.call()
        } else if (digit5.isEmpty()) {
            requestFocusDigit4Event.call()
        }
    }

    fun handleDigit6(digit6: String) {

        this.digit6 = digit6

        if (digit6.isEmpty()) {
            requestFocusDigit5Event.call()
        }
    }

    fun checkOtpValid() {

        if (digit1 == "1" && digit2 == "1" && digit3 == "1"
            && digit4 == "1" && digit5 == "1" && digit6 == "1"
        ) {

            sessionManager.isLogin = true
            goToMain.call()

        } else {
            wrongOtpEvent.call()
            requestFocusDigit1Event.call()
        }
    }
}