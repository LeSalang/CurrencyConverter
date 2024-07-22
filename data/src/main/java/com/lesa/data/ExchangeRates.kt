package com.lesa.data

data class ExchangeRates(
    val from: Currency,
    val to: Currency,
    val rates: Map<Currency, Double>
)

internal fun ExchangeRatesDB.toExchangeRates(): ExchangeRates {
    return ExchangeRates(
        from = Currency.valueOf(from),
        to = Currency.valueOf(to),
        rates = ratesMap.mapKeys {
            Currency.valueOf(it.key)
        }
    )
}
