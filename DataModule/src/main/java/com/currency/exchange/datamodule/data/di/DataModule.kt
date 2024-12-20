package com.currency.exchange.datamodule.data.di

import android.content.Context
import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.interfaces.ILocalRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.repositories.DataRepository
import com.currency.exchange.datamodule.data.repositories.LocalRepository
import com.currency.exchange.datamodule.data.repositories.SharedDataRepository
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
        currencyApi: CurrencyApi,
        countryApi: CountryApi,
        coroutineScope: CoroutineScope
    ) = DataRepository(
        appDatabase = appDatabase,
        currencyApi = currencyApi,
        countryApi = countryApi,
        coroutineScope = coroutineScope
    ) as IDataRepository

    @Singleton
    @Provides
    fun provideLocalDataRepository(@ApplicationContext context: Context) =
        LocalRepository(context) as ILocalRepository

    @Singleton
    @Provides
    fun provideSharedDataRepository() =
        SharedDataRepository() as ISharedDataRepository

}