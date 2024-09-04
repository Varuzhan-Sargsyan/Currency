package com.currency.exchange.datamodule.di

import com.currency.exchange.datamodule.domain.repositories.CurrencyRepository
import com.currency.exchange.datamodule.usecase.ICurrency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository() = CurrencyRepository() as ICurrency

}