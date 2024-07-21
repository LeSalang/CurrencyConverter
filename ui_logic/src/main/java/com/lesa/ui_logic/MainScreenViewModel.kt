package com.lesa.ui_logic

import androidx.lifecycle.ViewModel
import com.lesa.data.ExchangeRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ExchangeRateRepository,
    private val keyboardInputProcessor: KeyboardInputProcessor
) : ViewModel() {

    private val _result: MutableStateFlow<String> = MutableStateFlow("")
    val result: StateFlow<String>
        get() = _result

    private val exchangeRates = mapOf<CurrencyUi, Double>(CurrencyUi.USD to 1.0, CurrencyUi.RUB to 88.0)

    val sample = InputData(
        fromCurrency = CurrencyUi.USD,
        toCurrency = CurrencyUi.RUB,
        amount = "",
    )

    private val _input: MutableStateFlow<InputData> = MutableStateFlow(sample)
    val input: StateFlow<InputData>
        get() = _input

    fun onKeyboardClick(key: KeyboardKey) {
        val inputAmountStr = _input.value.amount
        _input.value = _input.value.copy(
            amount = keyboardInputProcessor.invoke(
                oldString = inputAmountStr,
                inputKey = key
            )
        )
        getExchangeResult()
    }

    private fun getExchangeResult(input: InputData = _input.value) {
        val rate = exchangeRates[input.toCurrency]
        if (rate == null) {
            _result.value = "error"
            return
        }
        val inputAmount = input.amount.toDoubleOrNull() ?: 0.0
        val result = inputAmount * rate
        _result.value = result.toString()
    }
}
