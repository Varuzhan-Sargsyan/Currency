package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.utils.navigateBack
import com.currency.exchange.app.ui.utils.screenFlow
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedDataRepository: ISharedDataRepository
) : ViewModel() {
    fun navigateBack() =
        sharedDataRepository.navigateBack()

    val screenFlow = sharedDataRepository.screenFlow()
}