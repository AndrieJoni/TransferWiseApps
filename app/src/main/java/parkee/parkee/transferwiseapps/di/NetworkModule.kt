package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.network.TransferWiseClient

val networkModule = module {

    single {
        TransferWiseClient.getMovieDbServices()
    }
}