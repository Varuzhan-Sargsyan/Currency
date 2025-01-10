package com.currency.exchange.app.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.ApplicationSettings
import com.currency.exchange.datamodule.domain.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: ISettingsRepository,
    private val sharedDataRepository: ICacheDataRepository
) : ViewModel() {
    val applicationSettingsFlow = settingsRepository.appSettingsFlow()

    fun theme(theme: Theme) =
        settingsRepository.applicationSettings(
            settingsRepository
                .applicationSettings()?.copy(theme = theme) ?: ApplicationSettings(theme = theme)
        )

    fun navigateBack() {
        sharedDataRepository.navigateBack()
    }
}