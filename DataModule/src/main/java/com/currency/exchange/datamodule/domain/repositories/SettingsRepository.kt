package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.ILocalRepository
import com.currency.exchange.datamodule.data.interfaces.flow
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val localRepository: ILocalRepository) : ISettingsRepository {
    companion object {
        private const val KEY_APPLICATION_SETTINGS = "application_settings"
    }

    init {
        if(!localRepository.contains(KEY_APPLICATION_SETTINGS))
            applicationSettings(ApplicationSettings.default())
    }

    override fun applicationSettings(applicationSettings: ApplicationSettings?) =
        applicationSettings?.let {
            localRepository.save(
                KEY_APPLICATION_SETTINGS,
                applicationSettings
            )
        } ?: localRepository.remove(KEY_APPLICATION_SETTINGS)

    override fun applicationSettings() =
        localRepository.load(KEY_APPLICATION_SETTINGS, ApplicationSettings::class.java)

    override fun appSettingsFlow() : Flow<ApplicationSettings?> =
        localRepository.flow<ApplicationSettings>(KEY_APPLICATION_SETTINGS)
}