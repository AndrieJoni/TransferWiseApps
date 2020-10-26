package parkee.parkee.transferwiseapps.data.repository

import parkee.parkee.transferwiseapps.data.network.exchangeRate.ExchangeRateService

class ExchageRateRepository(private val exchangeRateService: ExchangeRateService) {

    suspend fun getExchangeRate(source: String, target: String) =
        exchangeRateService.getExchangeRates(source, target)
}