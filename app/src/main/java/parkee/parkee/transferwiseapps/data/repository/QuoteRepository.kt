package parkee.parkee.transferwiseapps.data.repository

import parkee.parkee.transferwiseapps.data.network.quote.QuoteService

class QuoteRepository(private val quoteService: QuoteService) {

    suspend fun getQuote(parameter: Map<String, Any>) = quoteService.createQuote(parameter)

    suspend fun getQuoteById(quoteId: String) = quoteService.getQuoteById(quoteId)
}