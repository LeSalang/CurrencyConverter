package com.lesa.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [API Documentation](https://freecurrencyapi.com/docs/)
 */
interface Api {
    @GET(GET_LATEST_EXCHANGE_RATES)
    suspend fun getExchangeRates(
        @Query("base_currency") baseCurrency: String
    ): ExchangeRatesResponse
}

fun createApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
): Api {
    val json = Json { ignoreUnknownKeys = true }

    return retrofit(
        baseUrl = baseUrl,
        apiKey = apiKey,
        okHttpClient = okHttpClient,
        json = json
    ).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json = Json
): Retrofit {
    val jsonConverter: Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(ApiKeyInterceptor(apikey = apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverter)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}

const val GET_LATEST_EXCHANGE_RATES = "latest"
