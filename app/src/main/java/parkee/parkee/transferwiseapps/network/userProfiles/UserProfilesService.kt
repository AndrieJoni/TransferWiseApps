package parkee.parkee.transferwiseapps.network.userProfiles

import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface UserProfilesService {

    @POST("/v1/profiles")
    suspend fun getUserProfiles(@Body param: Map<String, Any>): UserProfilesPersonalDto
}