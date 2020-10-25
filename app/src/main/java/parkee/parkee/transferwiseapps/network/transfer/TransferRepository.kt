package parkee.parkee.transferwiseapps.network.transfer

class TransferRepository(private val transferService: TransferService) {

    suspend fun createTransfer(parameter: Map<String, Any>) =
        transferService.createTransfer(parameter)

    suspend fun createFund(profileId: String, transferId: String,parameter: Map<String, String>) =
        transferService.createFund(profileId, transferId,parameter)

    suspend fun getAllTransfer() = transferService.getAllTransfer()
}