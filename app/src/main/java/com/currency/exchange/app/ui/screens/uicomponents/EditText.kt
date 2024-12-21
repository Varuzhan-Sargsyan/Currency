package com.currency.exchange.app.ui.screens.uicomponents

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.groupViewTextColor
import com.currency.exchange.datamodule.domain.model.Theme

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
    textStyle: TextStyle = TextStyle(color = MaterialTheme.groupViewTextColor())
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().then(modifier),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        textStyle = textStyle,
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

@Preview(showBackground = true)
@Composable
fun EditTextPreview() {
    CurrencyAppTheme(Theme.light()) {
        Group("Title") {
            EditText(
                text = "Hello",
                onValueChange = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditTextPreviewDark() {
    CurrencyAppTheme(Theme.dark()) {
        GroupView("Title") {
            EditText(
                text = "Hello",
                onValueChange = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EditTextPreviewSystemDark() {
    CurrencyAppTheme(Theme.system()) {
        GroupView("Title") {
            EditText(
                text = "Hello",
                onValueChange = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun EditTextPreviewSystemLight() {
    CurrencyAppTheme(Theme.system()) {
        GroupView("Title") {
            EditText(
                text = "Hello",
                onValueChange = {}
            )
        }
    }
}