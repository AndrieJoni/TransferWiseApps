package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.network.borderlessAccount.BorderlessAccountsService
import parkee.parkee.transferwiseapps.repository.BorderlessAccountsRepository
import retrofit2.Retrofit

val borderlessAccountsModule = module {

    factory {
        BorderlessAccountsRepository(
            get<Retrofit>().create(BorderlessAccountsService::class.java)
        )
    }
}