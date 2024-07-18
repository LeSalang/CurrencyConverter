package com.lesa.currencyconverter

import com.lesa.network.Api
import com.lesa.network.createApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}
