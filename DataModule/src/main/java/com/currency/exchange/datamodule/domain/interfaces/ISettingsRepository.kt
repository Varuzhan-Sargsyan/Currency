package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun applicationSettings(applicationSettings: ApplicationSettings?)
    fun applicationSettings(): ApplicationSettings?
    fun appSettingsFlow(): Flow<ApplicationSettings?>
}