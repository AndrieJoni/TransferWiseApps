package parkee.parkee.transferwiseapps.data.repository

import parkee.parkee.transferwiseapps.data.network.user.UserService

class UserRepository(private val userService: UserService) {

    suspend fun getCurrentUser() = userService.getCurrentUser()
}