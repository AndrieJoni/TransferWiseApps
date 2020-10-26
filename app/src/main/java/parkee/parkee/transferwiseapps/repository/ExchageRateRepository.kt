package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.exchangeRate.ExchangeRateService

class ExchageRateRepository(private val exchangeRateService: ExchangeRateService) {

    suspend fun getExchangeRate(source: String, target: String) =
        exchangeRateService.getExchangeRates(source, target)
}