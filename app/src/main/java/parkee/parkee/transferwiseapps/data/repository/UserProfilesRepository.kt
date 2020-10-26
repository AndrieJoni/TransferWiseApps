package parkee.parkee.transferwiseapps.data.repository

import parkee.parkee.transferwiseapps.data.network.userProfiles.UserProfilesService

class UserProfilesRepository(private val userProfilesService: UserProfilesService) {

    suspend fun getUserProfiles() = userProfilesService.getUserProfiles()
}