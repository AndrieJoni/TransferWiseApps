package parkee.parkee.transferwiseapps.data.network.user

import retrofit2.http.GET

interface UserService {

    @GET("v1/me")
    suspend fun getCurrentUser(): UserDto
}