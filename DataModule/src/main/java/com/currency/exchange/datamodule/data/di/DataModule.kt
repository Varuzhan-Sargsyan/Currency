package com.currency.exchange.datamodule.data.di

import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.api.Api
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.repositories.DataRepository
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
    fun provideDataRepository(
        appDatabase: AppDatabase,
        api: Api
    ) = DataRepository(
        appDatabase = appDatabase,
        api = api
    ) as IDataRepository

}