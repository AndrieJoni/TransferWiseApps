package parkee.parkee.transferwiseapps.network.quote

import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface QuoteService {

    @POST("v1/quotes")
    suspend fun createQuote(
        @Body parameter: Map<String, Any>
    ): CreateQuoteResponseDto

}