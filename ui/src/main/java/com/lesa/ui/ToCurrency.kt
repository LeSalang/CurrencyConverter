package com.lesa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lesa.ui_logic.MainScreenViewModel

@Composable
fun ToCurrency(
    viewModel: MainScreenViewModel,
    currencyUi: com.lesa.ui_logic.CurrencyUi,
    modifier: Modifier = Modifier
) {
    var result = viewModel.result.collectAsState().value
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = currencyUi.drawableRes),
            contentDescription = stringResource(id = currencyUi.fullNameRes),
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = currencyUi.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "openList",
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = viewModel.result.collectAsState().value,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
        )
    }
}
