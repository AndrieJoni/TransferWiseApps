package parkee.parkee.transferwiseapps.ui.login

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.main.MainActivity
import parkee.parkee.transferwiseapps.ui.verificationOtp.VerificationOtpActivity

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {

        textViewTermsAndCondition.append("By continuing you accept our ")

        val text1 = SpannableString("Terms of use")

        text1.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text1.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)),
            0,
            text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textViewTermsAndCondition.append(text1)
        textViewTermsAndCondition.append(" and ")

        val text2 = SpannableString("Privacy Policy")
        text2.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text2.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)),
            0,
            text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textViewTermsAndCondition.append(text2)

        textViewTermsAndCondition.movementMethod = LinkMovementMethod.getInstance()
        textViewTermsAndCondition.highlightColor = Color.TRANSPARENT
    }

    private fun initListener() {

        textInputEditTextEmail.addTextChangedListener {
            loginViewModel.handleEmailTextWatcher(it.toString())
        }

        textInputEditTextPassword.addTextChangedListener {
            loginViewModel.handlePasswordTextWatcher(it.toString())
        }

        buttonLogin.setOnClickListener {
            loginViewModel.loginClicked()
        }
    }

    private fun initObserver() {

        loginViewModel.goToVerificationOtpEvent.observe(this, Observer {
            startActivity(Intent(this, VerificationOtpActivity::class.java))
        })

        loginViewModel.goToMain.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        loginViewModel.setButtonLoginEnabled.observe(this, {
            buttonLogin.isEnabled = it
        })

        loginViewModel.setEmailEmptyErrorEvent.observe(this, Observer {

            textInputLayoutEmail.isErrorEnabled = it
            textInputLayoutEmail.error = getString(R.string.error_must_be_filled)
        })

        loginViewModel.setEmailFormatErrorEvent.observe(this, Observer {

            textInputLayoutEmail.isErrorEnabled = it
            textInputLayoutEmail.error = getString(R.string.error_email_wrong_format)
        })

        loginViewModel.setPasswordEmptyErrorEvent.observe(this, Observer {

            textInputLayoutPassword.isErrorEnabled = it

            if (it)
                textInputLayoutPassword.error = getString(R.string.error_must_be_filled)
            else textInputLayoutPassword.error = ""

        })

        loginViewModel.clearEmailErrorEvent.observe(this, Observer {

            textInputLayoutEmail.isErrorEnabled = false
            textInputLayoutEmail.error = ""
        })
    }
}