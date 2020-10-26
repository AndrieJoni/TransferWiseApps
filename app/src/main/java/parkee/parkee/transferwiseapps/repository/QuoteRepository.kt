package parkee.parkee.transferwiseapps.repository

import parkee.parkee.transferwiseapps.network.quote.QuoteService

class QuoteRepository(private val quoteService: QuoteService) {

    suspend fun getQuote(parameter: Map<String, Any>) = quoteService.createQuote(parameter)

    suspend fun getQuoteById(quoteId: String) = quoteService.getQuoteById(quoteId)
}