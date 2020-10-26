package parkee.parkee.transferwiseapps.data.repository

import parkee.parkee.transferwiseapps.data.network.borderlessAccount.BorderlessAccountsService

class BorderlessAccountsRepository(private val borderlessAccountsService: BorderlessAccountsService) {

    suspend fun getAccountBalance(profileId: String) =
        borderlessAccountsService.getAccountBalance(profileId)

    suspend fun getAvailableCurrency() =
        borderlessAccountsService.getAvailableCurrency()
}