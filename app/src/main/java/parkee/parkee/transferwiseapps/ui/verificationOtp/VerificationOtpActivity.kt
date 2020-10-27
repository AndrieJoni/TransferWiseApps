package parkee.parkee.transferwiseapps.ui.verificationOtp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_verification_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.main.MainActivity

class VerificationOtpActivity : AppCompatActivity() {

    private val verificationOtpViewModel: VerificationOtpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_otp)
        initObserver()
        initListener()
    }

    private fun initObserver() {

        verificationOtpViewModel.goToMain.observe(this, Observer {

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        })

        verificationOtpViewModel.requestFocusDigit1Event.observe(this, Observer {
            textInputEditTextDigit1.requestFocus()
        })

        verificationOtpViewModel.requestFocusDigit2Event.observe(this, Observer {
            textInputEditTextDigit2.requestFocus()
        })

        verificationOtpViewModel.requestFocusDigit3Event.observe(this, Observer {
            textInputEditTextDigit3.requestFocus()
        })

        verificationOtpViewModel.requestFocusDigit4Event.observe(this, Observer {
            textInputEditTextDigit4.requestFocus()
        })

        verificationOtpViewModel.requestFocusDigit5Event.observe(this, Observer {
            textInputEditTextDigit5.requestFocus()
        })

        verificationOtpViewModel.requestFocusDigit6Event.observe(this, Observer {
            textInputEditTextDigit6.requestFocus()
        })

        verificationOtpViewModel.wrongOtpEvent.observe(this, Observer {

            textInputEditTextDigit1.setText("")
            textInputEditTextDigit2.setText("")
            textInputEditTextDigit3.setText("")
            textInputEditTextDigit4.setText("")
            textInputEditTextDigit5.setText("")
            textInputEditTextDigit6.setText("")

            Toast.makeText(this, getString(R.string.error_wrong_otp), Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListener() {

        materialToolbar.setNavigationOnClickListener {
            finish()
        }

        btnCheckOtp.setOnClickListener {
            verificationOtpViewModel.checkOtpValid()
        }

        textInputEditTextDigit1.addTextChangedListener {
            verificationOtpViewModel.handleDigit1(it.toString())
        }

        textInputEditTextDigit2.addTextChangedListener {
            verificationOtpViewModel.handleDigit2(it.toString())
        }

        textInputEditTextDigit3.addTextChangedListener {
            verificationOtpViewModel.handleDigit3(it.toString())
        }

        textInputEditTextDigit4.addTextChangedListener {
            verificationOtpViewModel.handleDigit4(it.toString())
        }

        textInputEditTextDigit5.addTextChangedListener {
            verificationOtpViewModel.handleDigit5(it.toString())
        }

        textInputEditTextDigit6.addTextChangedListener {
            verificationOtpViewModel.handleDigit6(it.toString())
        }

        textInputEditTextDigit1.setSelectAllOnFocus(true)
        textInputEditTextDigit2.setSelectAllOnFocus(true)
        textInputEditTextDigit3.setSelectAllOnFocus(true)
        textInputEditTextDigit4.setSelectAllOnFocus(true)
        textInputEditTextDigit5.setSelectAllOnFocus(true)
        textInputEditTextDigit6.setSelectAllOnFocus(true)

        textInputEditTextDigit1.setOnClickListener {
            textInputEditTextDigit1.selectAll()
        }

        textInputEditTextDigit2.setOnClickListener {
            textInputEditTextDigit2.selectAll()
        }

        textInputEditTextDigit3.setOnClickListener {
            textInputEditTextDigit3.selectAll()
        }

        textInputEditTextDigit4.setOnClickListener {
            textInputEditTextDigit4.selectAll()
        }

        textInputEditTextDigit5.setOnClickListener {
            textInputEditTextDigit5.selectAll()
        }

        textInputEditTextDigit6.setOnClickListener {
            textInputEditTextDigit6.selectAll()
        }
    }
}