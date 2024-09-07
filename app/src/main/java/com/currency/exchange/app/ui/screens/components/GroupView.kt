package com.currency.exchange.app.ui.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.theme.Elevations.noElevation
import com.currency.exchange.app.ui.theme.Paddings.medium
import com.currency.exchange.app.ui.theme.Paddings.normal
import com.currency.exchange.app.ui.theme.Shapes
import com.currency.exchange.app.ui.theme.Sizes.groupTitle
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.borderDefaultColor
import com.currency.exchange.app.ui.theme.groupViewBackgroundColor
import com.currency.exchange.app.ui.theme.groupViewTextColor
import com.currency.exchange.app.ui.theme.iconDefaultColor

@SuppressLint("ModifierParameter")
@Composable
fun GroupView(
    title: String? = null,
    modifier: Modifier = Modifier,
    modifierInside: Modifier = Modifier,
    shape: Shape = Shapes.listDefaultShapes,
    hasBorder: Boolean = false,
    elevation: CardElevation = noElevation(),
    onClick: OnClick? = null,
    content: @Composable () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        title?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.uppercase(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = normal),
                    fontSize = groupTitle,
                    color = MaterialTheme.iconDefaultColor()
                )
            }
            Spacer(modifier = Modifier.height(medium))
        }
        onClick?.let { clicker ->
            Card(
                shape = shape,
                onClick = clicker,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.groupViewBackgroundColor(),
                    contentColor = MaterialTheme.groupViewTextColor()
                ),
                border = if (hasBorder) BorderStroke(1.dp, MaterialTheme.borderDefaultColor()) else null,
                elevation = elevation
            ) {
                Column(modifier = modifierInside) {
                    content()
                }
            }
        } ?: Card(
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.groupViewBackgroundColor(),
                contentColor = MaterialTheme.groupViewTextColor()
            ),
            border = if (hasBorder) BorderStroke(1.dp, MaterialTheme.iconDefaultColor()) else null,
            elevation = elevation
        ) {
            Column(modifier = modifierInside) {
                content()
            }
        }
    }
}
