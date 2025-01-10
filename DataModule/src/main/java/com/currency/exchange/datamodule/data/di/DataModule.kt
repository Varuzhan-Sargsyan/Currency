package com.currency.exchange.datamodule.data.di

import android.content.Context
import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedPrefs
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.interfaces.IRemoteDataRepository
import com.currency.exchange.datamodule.data.repositories.LocalDataRepository
import com.currency.exchange.datamodule.data.repositories.SharedPrefs
import com.currency.exchange.datamodule.data.repositories.CacheDataRepository
import com.currency.exchange.datamodule.data.repositories.RemoteDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class, NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideDataScope() =
        CoroutineScope(Dispatchers.IO + CoroutineName("DataModuleScope"))

    @Singleton
    @Provides
    fun provideDataRepository(
        appDatabase: AppDatabase,
        coroutineScope: CoroutineScope
    ) = LocalDataRepository(
        appDatabase = appDatabase,
        coroutineScope = coroutineScope
    ) as ILocalDataRepository

    @Singleton
    @Provides
    fun provideSharedPrefsRepository(@ApplicationContext context: Context) =
        SharedPrefs(context) as ISharedPrefs

    @Singleton
    @Provides
    fun provideSharedDataRepository() =
        CacheDataRepository() as ICacheDataRepository

    @Singleton
    @Provides
    fun provideRemoteDataRepository(
        localDataRepository: ILocalDataRepository,
        currencyApi: CurrencyApi,
        countryApi: CountryApi,
        coroutineScope: CoroutineScope
    ) = RemoteDataRepository(
        localDataRepository = localDataRepository,
        currencyApi = currencyApi,
        countryApi = countryApi,
        coroutineScope = coroutineScope
    ) as IRemoteDataRepository
}