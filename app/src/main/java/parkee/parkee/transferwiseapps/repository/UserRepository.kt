package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.UserService

class UserRepository(private val userService: UserService) {

    suspend fun getCurrentUser() = userService.getCurrentUser()
}