package com.currency.exchange.datamodule.di

import com.currency.exchange.datamodule.data.datasource.AppDatabase
import com.currency.exchange.datamodule.domain.api.CurrencyApi
import com.currency.exchange.datamodule.domain.repositories.CurrencyRepository
import com.currency.exchange.datamodule.domain.repositories.RateRepository
import com.currency.exchange.datamodule.usecase.ICurrencyRepository
import com.currency.exchange.datamodule.usecase.IRateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class, NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        appDatabase: AppDatabase,
        currencyApi: CurrencyApi
    ) = CurrencyRepository(
        appDatabase = appDatabase,
        currencyApi = currencyApi
    ) as ICurrencyRepository

    @Singleton
    @Provides
    fun provideRateRepository(
        appDatabase: AppDatabase,
        currencyApi: CurrencyApi
    ) = RateRepository(
        appDatabase = appDatabase,
        currencyApi = currencyApi
    ) as IRateRepository

}