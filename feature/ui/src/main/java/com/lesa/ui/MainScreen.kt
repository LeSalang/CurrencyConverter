package com.lesa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.ui_logic.MainScreenViewModel
import com.lesa.ui_logic.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    MainScreen(
        mainScreenViewModel = viewModel(),
        modifier = modifier
    )
}

@Composable
private fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    modifier: Modifier = Modifier,
) {
    var pickerType by remember {
        mutableStateOf<PickerType?>(null)
    }
    val inputData = mainScreenViewModel.input.collectAsState().value
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(id = com.lesa.ui_logic.R.string.currency_converter),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
        Calculator(
            onCurrencySelectorClick = {
                pickerType = it
            },
            inputData = inputData,
            viewModel = mainScreenViewModel
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
        Keyboard(onClick = mainScreenViewModel::onKeyboardClick)
    }
    val shownPickerType = pickerType
    if (shownPickerType != null) {
        CurrencyPicker(
            onCompletion = {
                it?.let { currencyUi ->
                    when (shownPickerType) {
                        PickerType.FROM -> {
                            mainScreenViewModel.onChangedFromCurrency(currencyUi)
                        }
                        PickerType.TO -> {
                            mainScreenViewModel.onChangedToCurrency(currencyUi)
                        }
                    }
                }
                pickerType = null
            }
        )
    }
}
