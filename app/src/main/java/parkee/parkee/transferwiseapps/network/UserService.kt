package parkee.parkee.transferwiseapps.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("/v1/me")
    suspend fun getCurrentUser(): Response<ResponseBody>
}