package parkee.parkee.transferwiseapps.data.network.client

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import parkee.parkee.transferwiseapps.BuildConfig

class BaseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original: Request = chain.request()

        val requestBuilder: Request.Builder = original.newBuilder()
            .addHeader("Authorization", "Bearer " + BuildConfig.TRANSFERWISE_API_KEY)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}