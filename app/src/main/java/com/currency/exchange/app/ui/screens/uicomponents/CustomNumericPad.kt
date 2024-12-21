package com.currency.exchange.app.ui.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.extensions.OnString
import com.currency.exchange.app.ui.extensions.addLabel
import com.currency.exchange.app.ui.theme.Dimensions.numberPadButtonSize
import com.currency.exchange.app.ui.theme.Dimensions.numberPadTextSize
import com.currency.exchange.app.ui.theme.Dimensions.paddingBig
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Shapes.numberPadButtonShape
import com.currency.exchange.app.ui.theme.groupViewBackgroundColor

@Composable
fun CustomNumericPad(
    initialValue: String,
    onClose: OnClick,
    onValue: OnString
) {
    var inputValue by remember { mutableStateOf(initialValue) }

    Column(
        modifier = Modifier
            .padding(paddingNormal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the updated input value
        Text(
            text = inputValue,
            fontSize = numberPadTextSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(paddingNormal)
                .fillMaxWidth()
                .heightIn(min = 40.dp)
        )

        Spacer(modifier = Modifier.height(paddingNormal))

        // Grid for numeric buttons and other actions
        Row {
            Column {
                val buttons = listOf(
                    listOf("1", "2", "3"),
                    listOf("4", "5", "6"),
                    listOf("7", "8", "9"),
                    listOf(null, "0", ".")
                )

                // Display the number buttons grid
                buttons.forEach { row ->
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.size(paddingNormal))
                        row.forEach { label ->
                            label?.let {
                                NumericButton(label) {
                                    inputValue = inputValue.addLabel(label)
                                }
                            } ?: run {
                                EmptyButton()
                            }
                            Spacer(modifier = Modifier.size(paddingNormal))
                        }
                    }
                    Spacer(modifier = Modifier.size(paddingNormal))
                }

            }
            Spacer(modifier = Modifier.size(paddingBig))
            Column {
                IconButton(R.drawable.ic_outline_backspace_24) {
                    if (inputValue.isNotEmpty()) {
                        inputValue = inputValue.dropLast(1)
                    }
                }
                Spacer(modifier = Modifier.size(paddingNormal))
                NumericButton("C") {
                    inputValue = inputValue.addLabel("C")
                }
            }
        }
        Spacer(modifier = Modifier.size(paddingNormal))
        // Backspace and Save buttons row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(R.string.button_exit, onClose)
            TextButton(R.string.button_save) { onValue(inputValue) }
        }
        Spacer(modifier = Modifier.size(paddingNormal))
    }
}

@Composable
fun NumericButton(label: String, onClick: () -> Unit) {
    Card (
        onClick = onClick,
        modifier = Modifier
            .background(MaterialTheme.groupViewBackgroundColor(), shape = RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier.size(numberPadButtonSize)) {
            Text(
                modifier = Modifier.align(Alignment.Center),

                text = label,
                fontSize = numberPadTextSize
            )
        }
    }
}

@Composable
fun IconButton(id: Int, onBackspace: OnClick) {
    Card (
        onClick = { onBackspace() },
        modifier = Modifier
            .background(MaterialTheme.groupViewBackgroundColor(), shape = numberPadButtonShape)
    ) {
        Box(modifier = Modifier.size(numberPadButtonSize)) {
            Icon(
                painterResource(id),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                contentDescription = "Backspace",
            )
        }
    }
}

@Composable
fun EmptyButton() {
    Box(
        modifier = Modifier
            .size(numberPadButtonSize)
            .background(Color.Unspecified, shape = RoundedCornerShape(10.dp))
    ) {
    }
}

@Composable
fun TextButton(id: Int, onClick: OnClick) {
    Box(
        modifier = Modifier
            .padding(vertical = paddingNormal, horizontal = paddingBig)
            .clickable { onClick() }
    ) {
        Text(
            text = stringResource(id),
            modifier = Modifier
                .height(32.dp)
                .wrapContentWidth()
                .align(Alignment.Center),
            fontSize = numberPadTextSize,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun CustomNumericPadPreview() {
    CustomNumericPad(
        initialValue = "",
        onClose = {},
        onValue = {}
    )
}