package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.data.repositories.screenFlow
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedDataRepository: ISharedDataRepository,
    settingsRepository: ISettingsRepository,
) : ViewModel() {
    val screenFlow = sharedDataRepository.screenFlow()
    val applicationSettingsFlow = settingsRepository.appSettingsFlow()

    fun navigateBack() =
        sharedDataRepository.navigateBack()

    fun navigateToSettings() = sharedDataRepository.navigateTo(Screen.Settings)
}