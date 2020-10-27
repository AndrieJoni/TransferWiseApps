package parkee.parkee.transferwiseapps.data.network.exchangeRate

import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateService {

    @GET("v1/rates")
    suspend fun getExchangeRates(
        @Query("source") source: String,
        @Query("target") target: String,
    ): List<ExchangeRateDto>
}