package parkee.parkee.transferwiseapps.data.network.borderlessAccount

import retrofit2.http.GET
import retrofit2.http.Query

interface BorderlessAccountsService {

    @GET("v1/borderless-accounts")
    suspend fun getAccountBalance(@Query("profileId") page: String): List<AccountBalanceDto>

    @GET("v1/borderless-accounts/balance-currencies")
    suspend fun getAvailableCurrency(): List<CurrencyDto>

}