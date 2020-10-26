package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.repository.ExchageRateRepository
import parkee.parkee.transferwiseapps.network.exchangeRate.ExchangeRateService
import retrofit2.Retrofit

var exchangeRateModule = module {

    factory {
        ExchageRateRepository(get<Retrofit>().create(ExchangeRateService::class.java))
    }

}