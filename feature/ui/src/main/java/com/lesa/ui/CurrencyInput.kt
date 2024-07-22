package com.lesa.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.lesa.ui_logic.R

@Composable
fun CurrencyInput(
    isActive: Boolean,
    state: CurrencyInputState,
    currencyUi: com.lesa.ui_logic.CurrencyUi,
    onCurrencySelectorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CurrencySelector(
            currencyUi = currencyUi,
            onClick = onCurrencySelectorClick,
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
        val cardColor = if (isActive) {
            MaterialTheme.colorScheme.surfaceContainerHigh
        } else {
            MaterialTheme.colorScheme.surfaceContainerLow
        }
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_large)),
            colors = CardDefaults.cardColors(
                containerColor = cardColor,
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_large))
        ) {
            val text: String
            val color: Color
            when (state) {
                CurrencyInputState.Loading -> {
                    text = stringResource(R.string.loading)
                    color = MaterialTheme.colorScheme.onSurface
                }
                is CurrencyInputState.Error -> {
                    text = state.text
                    color = MaterialTheme.colorScheme.error
                }
                is CurrencyInputState.Success -> {
                    text = state.text
                    color = MaterialTheme.colorScheme.onSurface
                }
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                color = color,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}
