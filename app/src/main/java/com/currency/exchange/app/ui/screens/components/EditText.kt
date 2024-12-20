package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EditText(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text
    ),
    placeholder: String = "Enter text",
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = TextStyle(color = Color.Black)
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().then(modifier),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        textStyle = textStyle, // Text color
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(color = Color.Gray) // Placeholder color
                )
            }
            innerTextField()
        }
    )
}