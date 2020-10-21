package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.MainActivityViewModel
import parkee.parkee.transferwiseapps.network.UserService
import parkee.parkee.transferwiseapps.repository.UserRepository
import retrofit2.Retrofit

val userModule = module {

    factory {
        UserRepository(
            get<Retrofit>().create(UserService::class.java)
        )
    }

    viewModel { MainActivityViewModel(get()) }
}