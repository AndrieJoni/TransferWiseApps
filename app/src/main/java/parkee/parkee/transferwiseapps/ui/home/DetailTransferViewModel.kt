package parkee.parkee.transferwiseapps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import parkee.parkee.transferwiseapps.repository.ExchageRateRepository
import parkee.parkee.transferwiseapps.repository.QuoteRepository
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel

class DetailTransferViewModel(
    private var transferMoneyModel: TransferMoneyModel,
    private var quoteRepository: QuoteRepository,
    private var exchangeRateRepository: ExchageRateRepository
) : ViewModel() {

    var setFeeData = MutableLiveData<Double>()
    var setTransferDetail = MutableLiveData<TransferMoneyModel>()
    var setExchangeRateData = MutableLiveData<Double>()
    var setArrivedTime = MutableLiveData<String>()

    init {
        getData()
    }

    private fun getData() {

        viewModelScope.launch {

            supervisorScope {

                val getQuoteById = async {
                    quoteRepository.getQuoteById(transferMoneyModel.quoteId.toString())
                }

                val getExchangeRate = async {
                    exchangeRateRepository.getExchangeRate(
                        transferMoneyModel.sourceCurrency,
                        transferMoneyModel.targetCurrency
                    )
                }

                try {

                    setFeeData.value = getQuoteById.await().fee

                    setArrivedTime.value = getQuoteById.await().deliveryEstimate

                    setTransferDetail.value = transferMoneyModel

                    setExchangeRateData.value = getExchangeRate.await()[0].rate

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}