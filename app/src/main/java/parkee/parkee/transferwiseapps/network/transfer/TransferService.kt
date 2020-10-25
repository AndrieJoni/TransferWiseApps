package parkee.parkee.transferwiseapps.network.transfer

import retrofit2.http.*

@JvmSuppressWildcards
interface TransferService {

    @POST("v1/transfers")
    suspend fun createTransfer(
        @Body parameter: Map<String, Any>
    ): CreateTransferResponseDto

    @GET("v1/transfers")
    suspend fun getAllTransfer(
    ): List<CreateTransferResponseDto>

    @POST("v3/profiles/{profileId}/transfers/{transferId}/payments")
    suspend fun createFund(
        @Path("profileId") profileId: String,
        @Path("transferId") transferId: String,
        @Body parameter: Map<String, String>
    ): CreateFundResponseDto

    @POST("v1/transfers/{transferId}/cancel")
    suspend fun cancelTransfer(
        @Query("transferId") transferId: String
    ): CreateTransferResponseDto
}