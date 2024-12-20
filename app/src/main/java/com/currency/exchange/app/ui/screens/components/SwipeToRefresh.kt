package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.currency.exchange.app.ui.extensions.OnClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRefresh(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: OnClick,
    content: @Composable BoxScope.() -> Unit
) {
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox (
        modifier = modifier,
        state = pullRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        content = content
    )
}
