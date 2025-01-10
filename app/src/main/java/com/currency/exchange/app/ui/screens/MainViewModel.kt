package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.data.repositories.screenFlow
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.Screen
import com.currency.exchange.datamodule.domain.repositories.ReloadDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedDataRepository: ICacheDataRepository,
    private val reloadDataUseCase: ReloadDataUseCase,
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

        reloadData()
    }

    fun navigateBack() =
        sharedDataRepository.navigateBack()

    fun navigateToSettings() = sharedDataRepository.navigateTo(Screen.Settings)

    fun reloadData() {
        runInThread {
            reloadDataUseCase
                .invoke()
                .catch { }
                .collect {  }
        }
    }
}