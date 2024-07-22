package com.lesa.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lesa.ui_logic.InputData
import com.lesa.ui_logic.MainScreenViewModel
import com.lesa.ui_logic.R

@Composable
internal fun Calculator(
    onCurrencySelectorClick: (PickerType) -> Unit,
    inputData: InputData,
    viewModel: MainScreenViewModel
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_extra_large)))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            CurrencyInput(
                currencyUi = inputData.fromCurrency,
                isActive = true,
                state = CurrencyInputState.Success(inputData.amount),
                onCurrencySelectorClick = { onCurrencySelectorClick(PickerType.FROM) }
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    thickness = dimensionResource(id = R.dimen.divider_height),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
                IconButton(
                    onClick = {
                        viewModel.swapCurrencies()
                    },
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        disabledContentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_swap),
                        contentDescription = stringResource(id = R.string.swap),
                    )
                }
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
            CurrencyInput(
                isActive = false,
                state = viewModel.result.collectAsState().value.toCurrencyInputState(),
                currencyUi = inputData.toCurrency,
                onCurrencySelectorClick = { onCurrencySelectorClick(PickerType.TO) }
            )
        }
    }
}
