package parkee.parkee.transferwiseapps

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import parkee.parkee.transferwiseapps.di.*

class TransferWiseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@TransferWiseApplication)

            ///CoreModule
            modules(networkModule)

            modules(
                userModule,
                borderlessAccountsModule,
                quoteModule,
                transferMoneyModule,
                exchangeRateModule,
                homeModule,
                detailTransferModule,
                recipientsModules,
            )
        }
    }
}