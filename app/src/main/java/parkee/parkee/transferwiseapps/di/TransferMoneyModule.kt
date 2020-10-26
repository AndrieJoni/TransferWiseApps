package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.repository.TransferRepository
import parkee.parkee.transferwiseapps.network.transfer.TransferService
import parkee.parkee.transferwiseapps.ui.transferMoney.TransferMoneyViewModel
import retrofit2.Retrofit

var transferMoneyModule = module {

    factory {
        TransferRepository(get<Retrofit>().create(TransferService::class.java))
    }

    viewModel { TransferMoneyViewModel(get(), get(), get(), get(),get()) }
}