package com.currency.exchange.datamodule.domain.di

import com.currency.exchange.datamodule.data.di.DataModule
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.interfaces.ILocalRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.repositories.CurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.repositories.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModule::class])
class DomainModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        dataRepository: IDataRepository,
        sharedDataRepository: ISharedDataRepository,
        localRepository: ILocalRepository
    ) = CurrencyRepository(
            dataRepository = dataRepository,
            sharedDataRepository = sharedDataRepository,
            localRepository = localRepository
        ) as ICurrencyRepository

    @Singleton
    @Provides
    fun provideSettingsRepository(localRepository: ILocalRepository) =
        SettingsRepository(localRepository = localRepository) as ISettingsRepository
}