package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.data.network.client.TransferWiseClient

val networkModule = module {

    single {
        TransferWiseClient.getTransferWiseServices()
    }
}