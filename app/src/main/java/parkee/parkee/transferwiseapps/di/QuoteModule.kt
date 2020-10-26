package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.data.network.quote.QuoteService
import parkee.parkee.transferwiseapps.data.repository.QuoteRepository
import retrofit2.Retrofit

var quoteModule = module {

    factory {
        QuoteRepository(get<Retrofit>().create(QuoteService::class.java))
    }


}