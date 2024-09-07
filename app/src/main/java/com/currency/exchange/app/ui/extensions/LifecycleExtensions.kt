package com.currency.exchange.app.ui.extensions

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun ViewModel.runInThread(block: suspend () -> Unit) =
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }

fun ComponentActivity.runInThread(block: suspend () -> Unit) =
    lifecycleScope.launch(Dispatchers.IO) {
        block()
    }

fun ComponentActivity.runInMainThread(block: suspend () -> Unit) =
    lifecycleScope.launch(Dispatchers.Main) {
        block()
    }