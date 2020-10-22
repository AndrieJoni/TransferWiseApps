package parkee.parkee.transferwiseapps.network.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import parkee.parkee.transferwiseapps.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object TransferWiseClient {

    private fun okHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor())
            .addInterceptor(interceptor)
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .build()
    }

    fun getTransferWiseServices(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_SERVER_URL)
            .client(okHttpClient())
            .build()
    }

}