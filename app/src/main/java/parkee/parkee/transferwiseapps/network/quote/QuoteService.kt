package parkee.parkee.transferwiseapps.network.quote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@JvmSuppressWildcards
interface QuoteService {

    @POST("v1/quotes")
    suspend fun createQuote(
        @Body parameter: Map<String, Any>
    ): CreateQuoteResponseDto

    @GET("v1/quotes/{quoteId}")
    suspend fun getQuoteById(
        @Path("quoteId") quoteId: String
    ): CreateQuoteResponseDto
}