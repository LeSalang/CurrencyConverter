package com.lesa.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lesa.ui_logic.KeyboardKey
import com.lesa.ui_logic.keyboardKeys
import java.text.DecimalFormatSymbols

@Composable
fun Keyboard(
    onClick: (KeyboardKey) -> Unit,
    modifier: Modifier = Modifier,
) {
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
fun KeyboardButton(
    keyboardKey: KeyboardKey,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    when (keyboardKey) {
        is KeyboardKey.Digit -> Card(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClick)
        ) {
            Text(
                text = keyboardKey.digit.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            )
        }
        KeyboardKey.Delete -> Card(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClick)
        ) {
            Text(
                text = "DELETE",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            )
        }
        KeyboardKey.Separator -> Card(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClick)
        ) {
            Text(
                text = DecimalFormatSymbols.getInstance().decimalSeparator.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            )
        }
    }
}
