package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.data.network.exchangeRate.ExchangeRateService
import parkee.parkee.transferwiseapps.data.repository.ExchageRateRepository
import retrofit2.Retrofit

var exchangeRateModule = module {

    factory {
        ExchageRateRepository(get<Retrofit>().create(ExchangeRateService::class.java))
    }

}