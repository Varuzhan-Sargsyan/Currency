package com.currency.exchange.app.ui.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.OnString
import com.currency.exchange.app.ui.extensions.OnTheme
import com.currency.exchange.app.ui.theme.Dimensions.defaultFieldHeight
import com.currency.exchange.app.ui.theme.Dimensions.iconSize
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.groupViewBackgroundColor
import com.currency.exchange.app.ui.theme.iconDefaultColor
import com.currency.exchange.app.ui.utils.allNames
import com.currency.exchange.app.ui.utils.toStringValue
import com.currency.exchange.datamodule.domain.model.Theme

@Composable
fun DropDownView(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    labelName: String,
    labelValue: String,
    items: List<String>,
    onSelect: OnString,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    
    var text = labelValue

    Row(
        modifier = Modifier
            .clickable { expanded = true }
            .padding(horizontal = paddingNormal)
            .heightIn(defaultFieldHeight)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        imageVector?.let {
            Icon(
                imageVector = it,
                modifier = Modifier.size(iconSize),
                contentDescription = null,
                tint = MaterialTheme.iconDefaultColor()
            )
            SpacerH(width = paddingNormal)
        }
        TextFieldLabel(
            text = labelName,
            modifier = Modifier
                .weight(1f)
        )
        TextFieldLabel(
            text = text,
        )
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Click to select",
        )
        
        DropdownMenu(
            modifier = Modifier
                .width((LocalConfiguration.current.screenWidthDp * 0.92).dp)
                .background(MaterialTheme.groupViewBackgroundColor()),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row {
                            TextFieldLabel(
                                text = item,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            if (item == text)
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Checked",
                                    modifier = Modifier.size(iconSize),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                        }
                    },
                    onClick = {
                        expanded = false
                        text = item
                        onSelect(item)
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeFieldView(
    theme: Theme,
    modifier: Modifier = Modifier,
    onSelect: OnTheme,
) {
    val all = Theme.all()
    val allNames = all.allNames()
    DropDownView(
        imageVector = if(theme.isDarkTheme() || theme.isSystemTheme() && isSystemInDarkTheme()) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
        labelName = stringResource(R.string.appearance),
        labelValue = theme.toStringValue(),
        modifier = modifier,
        items = allNames,
        onSelect = { name ->
            var index = allNames.indexOf(name)
            if(index == -1) index = 0
            onSelect(all[index])
        }
    )
}
