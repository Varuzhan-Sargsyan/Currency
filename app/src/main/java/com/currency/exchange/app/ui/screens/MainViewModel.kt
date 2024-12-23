package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.data.repositories.screenFlow
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedDataRepository: ISharedDataRepository,
    private val currencyRepository: ICurrencyRepository,
    settingsRepository: ISettingsRepository,
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady as StateFlow<Boolean>

    val screenFlow = sharedDataRepository.screenFlow()
    val applicationSettingsFlow = settingsRepository.appSettingsFlow()

    init {
        runInThread {
            delay(1500)
            _isReady.value = true
        }
    }

    fun navigateBack() =
        sharedDataRepository.navigateBack()

    fun navigateToSettings() = sharedDataRepository.navigateTo(Screen.Settings)

    fun reload() =
        runInThread {
            currencyRepository.reload()
        }
}