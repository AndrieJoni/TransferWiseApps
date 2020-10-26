package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.data.network.borderlessAccount.BorderlessAccountsService
import parkee.parkee.transferwiseapps.data.repository.BorderlessAccountsRepository
import parkee.parkee.transferwiseapps.ui.currency.ChooseCurrencyViewModel
import retrofit2.Retrofit

val borderlessAccountsModule = module {

    factory {
        BorderlessAccountsRepository(
            get<Retrofit>().create(BorderlessAccountsService::class.java)
        )
    }

    viewModel { ChooseCurrencyViewModel(get()) }
}