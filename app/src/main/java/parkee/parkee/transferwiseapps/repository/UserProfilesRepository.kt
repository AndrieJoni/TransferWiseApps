package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.userProfiles.UserProfilesService

class UserProfilesRepository(private val userProfilesService: UserProfilesService) {

    suspend fun getUserProfiles(parameter: Map<String, Any>) =
        userProfilesService.getUserProfiles(
            parameter
        )
}