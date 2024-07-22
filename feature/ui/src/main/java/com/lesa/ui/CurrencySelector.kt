package com.lesa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lesa.ui_logic.CurrencyUi
import com.lesa.ui_logic.R

@Composable
internal fun CurrencySelector(
    currencyUi: CurrencyUi,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_large)))
            .clickable(onClick = onClick)
            .padding(dimensionResource(id = R.dimen.padding_extra_small))
    ) {
        Image(
            painter = painterResource(id = currencyUi.drawableRes),
            contentDescription = stringResource(id = currencyUi.fullNameRes),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_medium))
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = currencyUi.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = stringResource(id = R.string.open_currency_list),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
