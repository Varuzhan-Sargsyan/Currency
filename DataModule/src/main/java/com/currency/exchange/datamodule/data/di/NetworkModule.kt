package com.currency.exchange.datamodule.data.di

import android.content.Context
import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.api.RequestInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val CURRENCY_URL = "https://api.frankfurter.app/"
const val COUNTRY_URL = "https://restcountries.com/"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideRequestInterceptor() = RequestInterceptor() as Interceptor

    @CurrencyRetrofit
    @Provides
    @Singleton
    fun provideCurrencyRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @CurrencyOkHttp okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
            .baseUrl(CURRENCY_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @CountryRetrofit
    @Provides
    @Singleton
    fun provideCountryRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @CountryOkHttp okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(COUNTRY_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @CurrencyOkHttp
    @Provides
    @Singleton
    fun providesCurrencyOkHttpClient(
        @ApplicationContext context: Context,
        requestInterceptor: Interceptor
    ) : OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(requestInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (true) request.newBuilder() // make default to true till i figure out how to inject network status
                    .header("Cache-Control", "public, max-age=" + 5).build()
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @CountryOkHttp
    @Provides
    @Singleton
    fun providesCountryOkHttpClient(
        @ApplicationContext context: Context,
        requestInterceptor: Interceptor
    ) : OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(requestInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (true) request.newBuilder() // make default to true till i figure out how to inject network status
                    .header("Cache-Control", "public, max-age=" + 5).build()
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson() : Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson) : GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideCurrencyApi(@CurrencyRetrofit retrofit: Retrofit) : CurrencyApi =
        retrofit.create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideCountryApi(@CountryRetrofit retrofit: Retrofit) : CountryApi =
        retrofit.create(CountryApi::class.java)

}