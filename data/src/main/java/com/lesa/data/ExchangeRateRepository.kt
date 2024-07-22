package com.lesa.data

import android.content.Context
import com.lesa.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ExchangeRateRepository {
    suspend fun getExchangeRatesFromApi(fromCurrency: Currency): Map<Currency, Double>
    fun loadFromDataStore(): Flow<ExchangeRates>
    suspend fun saveToDataStore(
        from: Currency,
        to: Currency,
        rates: Map<Currency, Double>
    )
}

class ExchangeRateRepositoryImpl @Inject constructor(
    private val api: Api,
    private val context: Context
) : ExchangeRateRepository {
    override suspend fun getExchangeRatesFromApi(fromCurrency: Currency): Map<Currency, Double> {
        val response = api.getExchangeRates(baseCurrency = fromCurrency.name).data
        val exchangeRates = response.mapNotNull { pair ->
            val key = Currency.entries.firstOrNull {
                pair.key == it.name
            }
            if (key != null && key != fromCurrency) {
                key to pair.value
            } else {
                null
            }
        }.toMap()
        return exchangeRates
    }

    override fun loadFromDataStore(): Flow<ExchangeRates> {
        return context.exchangeRatesDataStore.data.map { exchangeRatesDB ->
            exchangeRatesDB.toExchangeRates()
        }
    }

    override suspend fun saveToDataStore(
        from: Currency,
        to: Currency,
        rates: Map<Currency, Double>
    ) {
        context.exchangeRatesDataStore.updateData { currentData ->
            currentData.toBuilder()
                .setFrom(from.name)
                .setTo(to.name)
                .putAllRates(
                    rates.mapKeys {
                        it.key.name
                    }
                )
                .build()
        }
    }
}
