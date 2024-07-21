package com.lesa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lesa.ui_logic.CurrencyUi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun CurrencyPicker(
    onCompletion: (CurrencyUi?) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onCompletion(null) },
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        )
    ) {
        CurrencyPickerList(
            onCurrencySelected = onCompletion
        )
    }
}

@Composable
private fun CurrencyPickerList(
    onCurrencySelected: (CurrencyUi) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
    ) {
        items(items = CurrencyUi.entries) { currency ->
            CurrencyPickerItem(
                currency = currency,
                onClick = { onCurrencySelected(currency) }
            )
        }
    }
}

@Composable
private fun CurrencyPickerItem(
    currency: CurrencyUi,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = currency.drawableRes),
            contentDescription = stringResource(id = currency.fullNameRes),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Column {
            Text(
                text = currency.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = stringResource(id = currency.fullNameRes),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        }
    }
}
