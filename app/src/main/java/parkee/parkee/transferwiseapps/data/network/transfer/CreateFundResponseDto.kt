package parkee.parkee.transferwiseapps.data.network.transfer

import com.google.gson.annotations.SerializedName


data class CreateFundResponseDto(
    @SerializedName("errorCode")
    var errorCode: Any = Any(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("type")
    var type: String = ""
)