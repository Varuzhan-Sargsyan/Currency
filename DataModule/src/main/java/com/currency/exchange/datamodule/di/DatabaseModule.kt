package com.currency.exchange.datamodule.di

import android.content.Context
import androidx.room.Room
import com.currency.exchange.datamodule.data.datasource.buildAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.buildAppDatabase(context)

}