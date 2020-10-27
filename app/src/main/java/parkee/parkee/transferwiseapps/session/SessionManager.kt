package parkee.parkee.transferwiseapps.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    var isLogin: Boolean
        get() = pref.getBoolean(IS_LOG_IN, false)
        set(logged) {
            pref.edit().putBoolean(IS_LOG_IN, logged).apply()
        }

    fun resetSession() {
        pref.edit().clear().apply()
    }

    companion object {

        const val IS_LOG_IN = "IsLogIn"
        const val PREF_NAME = "TransferWiseSession"
        const val PRIVATE_MODE = 0
    }
}
