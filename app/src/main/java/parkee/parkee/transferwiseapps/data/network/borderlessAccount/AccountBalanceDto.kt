package parkee.parkee.transferwiseapps.data.network.borderlessAccount

import com.google.gson.annotations.SerializedName


data class AccountBalanceDto(
    @SerializedName("active")
    var active: Boolean = false,
    @SerializedName("balances")
    var balanceDtos: List<BalanceDto> = listOf(),
    @SerializedName("creationTime")
    var creationTime: String = "",
    @SerializedName("eligible")
    var eligible: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("modificationTime")
    var modificationTime: String = "",
    @SerializedName("profileId")
    var profileId: Int = 0,
    @SerializedName("recipientId")
    var recipientId: Int = 0
)