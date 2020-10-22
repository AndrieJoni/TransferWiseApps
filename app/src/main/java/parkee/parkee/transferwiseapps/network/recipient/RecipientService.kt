package parkee.parkee.transferwiseapps.network.recipient

import parkee.parkee.transferwiseapps.network.borderlessAccount.AccountBalanceDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipientService {

    @GET("/v1/account-requirements")
    suspend fun getFormRequirments(
        @Query("source") source: String,
        @Query("target") target: String,
        @Query("sourceAmount") amount: Int = 1000,
    ): List<AccountBalanceDto>
}