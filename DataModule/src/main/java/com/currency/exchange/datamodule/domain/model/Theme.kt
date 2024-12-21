package com.currency.exchange.datamodule.domain.model

data class Theme(private var value: Int = 0) {

    companion object {
        const val LIGHT = 0
        const val DARK = 1
        const val SYSTEM = 2

        var theme: Theme = light()
        fun isDarkTheme() = theme.isDarkTheme()
        fun isSystemTheme() = theme.isSystemTheme()

        operator fun invoke(value: Int) : Theme {
            require(value in LIGHT..SYSTEM) { "Invalid theme value: $value" }
            return Theme(value)
        }

        fun light() = Theme(LIGHT)
        fun dark() = Theme(DARK)
        fun system() = Theme(SYSTEM)

        fun all() = listOf(light(), dark(), system())
    }

    operator fun invoke() : Int {
        return value
    }

    fun isDarkTheme() = value == DARK
    fun isSystemTheme() = value == SYSTEM
}