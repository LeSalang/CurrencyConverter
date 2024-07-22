package com.lesa.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.ui_logic.InputData
import com.lesa.ui_logic.MainScreenViewModel

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
    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeMode(
                pickerType = { pickerType = it },
                inputData = inputData,
                viewModel = mainScreenViewModel,
                modifier = modifier
            )
        }
        else -> {
            PortraitMode(
                pickerType = { pickerType = it },
                inputData = inputData,
                viewModel = mainScreenViewModel,
                modifier = modifier
            )
        }
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

@Composable
private fun Headline() {
    Text(
        text = "Currency converter",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PortraitMode(
    pickerType: (PickerType) -> Unit,
    inputData: InputData,
    viewModel: MainScreenViewModel,

    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(16.dp)
    ) {
        Headline()
        Spacer(modifier = Modifier.size(16.dp))
        Calculator(
            onCurrencySelectorClick = {
                pickerType(it)
            },
            inputData = inputData,
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.size(16.dp))
        Keyboard(onClick = viewModel::onKeyboardClick)
    }
}

@Composable
private fun LandscapeMode(
    pickerType: (PickerType) -> Unit,
    inputData: InputData,
    viewModel: MainScreenViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
                .fillMaxSize(0.6f)
        ) {
            Headline()
            Spacer(modifier = Modifier.size(16.dp))
            Calculator(
                onCurrencySelectorClick = {
                    pickerType(it)
                },
                inputData = inputData,
                viewModel = viewModel
            )
        }
        Keyboard(onClick = viewModel::onKeyboardClick,)
    }
}
