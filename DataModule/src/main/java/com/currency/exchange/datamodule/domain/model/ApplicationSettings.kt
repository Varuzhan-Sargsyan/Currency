package com.currency.exchange.datamodule.domain.model

data class ApplicationSettings(
    val theme: Theme
) {
    companion object {
        fun default() = ApplicationSettings(Theme.light())
    }
}
