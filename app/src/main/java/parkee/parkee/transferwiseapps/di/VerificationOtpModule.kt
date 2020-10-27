package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.ui.verificationOtp.VerificationOtpViewModel

var verificationOtpModule = module {

    viewModel { VerificationOtpViewModel(get()) }
}