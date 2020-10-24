package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.quote.QuoteService

class QuoteRepository(private val quoteService: QuoteService) {

    suspend fun getTemporaryQuote(parameter: Map<String, Any>) = quoteService.createQuote(parameter)
}