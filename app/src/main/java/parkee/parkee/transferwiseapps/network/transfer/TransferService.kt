package parkee.parkee.transferwiseapps.network.transfer

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

@JvmSuppressWildcards
interface TransferService {

    @POST("v1/transfers")
    suspend fun createTransfer(
        @Body parameter: Map<String, Any>
    ): CreateTransferResponseDto

    @POST("v3/profiles/{profileId}/transfers/{transferId}/payments")
    suspend fun createFund(
        @Query("profileId") profileId: String,
        @Query("transferId") transferId: String
    ): CreateFundResponseDto

    @POST("v1/transfers/{transferId}/cancel")
    suspend fun cancelTransfer(
        @Query("transferId") transferId: String
    ): CreateTransferResponseDto
}