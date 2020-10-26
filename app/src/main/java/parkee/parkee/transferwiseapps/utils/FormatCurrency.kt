package parkee.parkee.transferwiseapps.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object FormatCurrency {

    fun convert(amount: Long): String {
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','
        symbols.groupingSeparator = '.'
        val decimalFormat = DecimalFormat("##,###,###", symbols)
        decimalFormat.decimalFormatSymbols = symbols
        return decimalFormat.format(amount)
    }
}