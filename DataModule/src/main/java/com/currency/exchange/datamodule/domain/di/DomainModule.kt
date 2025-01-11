package com.currency.exchange.datamodule.domain.di

import com.currency.exchange.datamodule.data.di.DataModule
import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.interfaces.IRemoteDataRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedPrefs
import com.currency.exchange.datamodule.domain.repositories.CurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.interfaces.RateFlow
import com.currency.exchange.datamodule.domain.repositories.RateFlowUseCase
import com.currency.exchange.datamodule.domain.repositories.ReloadDataUseCase
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
    fun provideCurrencyRepository(localDataRepository: ILocalDataRepository) =
        CurrencyRepository(localDataRepository = localDataRepository) as ICurrencyRepository

    @Singleton
    @Provides
    fun provideSettingsRepository(
        sharedPreferences: ISharedPrefs,
        cacheDataRepository: ICacheDataRepository,
    ) = SettingsRepository(
            sharedPrefs = sharedPreferences,
            cacheDataRepository = cacheDataRepository
        ) as ISettingsRepository

    @Singleton
    @Provides
    fun provideReloadDataUseCase(
        remoteDataRepository: IRemoteDataRepository,
        localDataRepository: ILocalDataRepository
    ) = ReloadDataUseCase(
        remoteDataRepository = remoteDataRepository,
        localDataRepository = localDataRepository
    )

    @Singleton
    @Provides
    fun provideRateFlowUseCase(
        localDataRepository: ILocalDataRepository
    ) = RateFlowUseCase(
        localDataRepository = localDataRepository
    ) as RateFlow
}