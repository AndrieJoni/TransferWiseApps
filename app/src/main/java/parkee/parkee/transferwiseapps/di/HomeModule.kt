package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.ui.home.HomeViewModel

val homeModule = module {
    viewModel { HomeViewModel(get(), get(),get()) }
}