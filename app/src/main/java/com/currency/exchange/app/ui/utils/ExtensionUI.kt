package com.currency.exchange.app.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.Dimensions
import com.currency.exchange.app.ui.theme.Dimensions.bigIconSize
import com.currency.exchange.app.ui.theme.Dimensions.noPadding
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.Currency.Companion.DEFAULT_COUNTRY_CODE
import com.currency.exchange.datamodule.domain.model.Currency.Companion.DEFAULT_COUNTRY_FLAG
import com.currency.exchange.datamodule.domain.model.Currency.Companion.DEFAULT_COUNTRY_NAME

fun Modifier.iconModifier(
    iconSize: Dp = Dimensions.iconSize,
    iconPadding: Dp = Dimensions.iconPadding
) = this.size(iconSize).padding(iconPadding)

fun Modifier.clickableIconModifier(
    iconSize: Dp = bigIconSize,
    iconPadding: Dp = Dimensions.iconPadding,
    onClick: OnClick
) = this.size(iconSize).clickable { onClick() }.padding(iconPadding)

@Composable
fun emptyCurrency() =
    Currency(
        code = stringResource(R.string.empty_currency_code),
        name = stringResource(R.string.empty_currency_name),
        countryName = DEFAULT_COUNTRY_NAME,
        countryCode = DEFAULT_COUNTRY_CODE,
        flag = DEFAULT_COUNTRY_FLAG)
