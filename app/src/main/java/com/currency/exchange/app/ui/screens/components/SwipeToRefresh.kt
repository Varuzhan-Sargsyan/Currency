package com.currency.exchange.app.ui.screens.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.currency.exchange.app.ui.extensions.OnClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRefresh(
    modifier: Modifier = Modifier,
    onRefresh: OnClick,
    content: @Composable () -> Unit
) {
    var isRefreshing = remember { mutableStateOf(false) }
    val pullRefreshState = remember { PullToRefreshState() }

    PullToRefreshBox (
        modifier = modifier,
        state = pullRefreshState,
        isRefreshing = isRefreshing.value,
        onRefresh = onRefresh
    ) {
        content()
    }
}
