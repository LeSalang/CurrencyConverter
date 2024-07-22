package com.lesa.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lesa.ui_logic.KeyboardKey
import com.lesa.ui_logic.R
import com.lesa.ui_logic.keyboardKeys
import java.text.DecimalFormatSymbols

@Composable
fun Keyboard(onClick: (KeyboardKey) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        keyboardKeys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                row.forEach { keyboardKey ->
                    KeyboardButton(
                        keyboardKey = keyboardKey,
                        onClick = { onClick(keyboardKey) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun KeyboardButton(
    keyboardKey: KeyboardKey,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    when (keyboardKey) {
        is KeyboardKey.Digit -> DigitButton(modifier, onClick, keyboardKey)
        KeyboardKey.Delete -> DeleteButton(modifier, onClick)
        KeyboardKey.Separator -> SeparatorButton(modifier, onClick)
    }
}

@Composable
private fun DigitButton(
    modifier: Modifier,
    onClick: () -> Unit,
    keyboardKey: KeyboardKey.Digit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        modifier = modifier.clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            text = keyboardKey.digit.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.W400,
            modifier = Modifier.fillMaxSize()
                .wrapContentHeight()
        )
    }
}

@Composable
private fun SeparatorButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        modifier = modifier.clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            text = DecimalFormatSymbols.getInstance().decimalSeparator.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxSize()
                .wrapContentHeight()
        )
    }
}

@Composable
private fun DeleteButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        modifier = modifier.clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_delete),
                contentDescription = "delete",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(MaterialTheme.typography.displaySmall.fontSize.value.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
