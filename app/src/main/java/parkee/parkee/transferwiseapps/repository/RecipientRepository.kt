package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.recipient.RecipientService

class RecipientRepository(private val recipientService: RecipientService) {

    suspend fun getFormRequirments(source: String, target: String) =
        recipientService.getFormRequirments(source, target)
}