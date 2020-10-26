package parkee.parkee.transferwiseapps.data.network.userProfiles

import retrofit2.http.GET

interface UserProfilesService {

    @GET("v1/profiles")
    suspend fun getUserProfiles(): List<UserProfilesPersonalDto>
}