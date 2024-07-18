package com.lesa.data

import com.lesa.network.Api
import javax.inject.Inject

interface ExchangeRateRepository {
    suspend fun getExchangeRates(currency: Currency): Map<Currency, Double>
}

class ExchangeRateRepositoryImpl @Inject constructor(
    private val api: Api
) : ExchangeRateRepository {
    override suspend fun getExchangeRates(currency: Currency): Map<Currency, Double> {
        val response = api.getExchangeRates(baseCurrency = currency.name).data
        return response.mapNotNull { pair ->
            val key = Currency.entries.firstOrNull {
                pair.key == it.name
            }
            if (key != null && key != currency) {
                key to pair.value
            } else {
                null
            }
        }.toMap()
    }
}
