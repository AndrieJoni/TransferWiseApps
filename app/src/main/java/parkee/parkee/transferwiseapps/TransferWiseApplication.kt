package parkee.parkee.transferwiseapps

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import parkee.parkee.transferwiseapps.di.networkModule
import parkee.parkee.transferwiseapps.di.userModule

class TransferWiseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@TransferWiseApplication)

            ///CoreModule
            modules(networkModule)

            modules(userModule)
        }
    }
}