package com.lesa.currencyconverter

import android.content.Context
import com.lesa.data.ExchangeRateRepository
import com.lesa.data.ExchangeRateRepositoryImpl
import com.lesa.network.Api
import com.lesa.network.createApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSWApi(okHttpClient: OkHttpClient?): Api {
        return createApi(
            baseUrl = BuildConfig.API_BASE_URL,
            apiKey = BuildConfig.API_KEY,
            okHttpClient = okHttpClient
        )
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: Api, @ApplicationContext context: Context): ExchangeRateRepository {
        return ExchangeRateRepositoryImpl(api = api, context = context)
    }
}
