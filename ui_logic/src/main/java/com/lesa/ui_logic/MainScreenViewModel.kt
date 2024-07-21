package com.lesa.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.data.ExchangeRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ExchangeRateRepository,
    private val keyboardInputProcessor: KeyboardInputProcessor
) : ViewModel() {
    private val initialInput = InputData(
        fromCurrency = CurrencyUi.USD,
        toCurrency = CurrencyUi.RUB,
        amount = "",
    )

    private val _input: MutableStateFlow<InputData> = MutableStateFlow(initialInput)
    val input: StateFlow<InputData>
        get() = _input

    private val _result: MutableStateFlow<ExchangeResultState> = MutableStateFlow(ExchangeResultState.Loading)
    val result: StateFlow<ExchangeResultState>
        get() = _result

    private var exchangeRates = mapOf<CurrencyUi, Double>()

    init {
        getExchangeRates(input.value.fromCurrency)
    }

    fun swapCurrencies() {
        val currentResult = result.value
        _input.value = InputData(
            fromCurrency = input.value.toCurrency,
            toCurrency = input.value.fromCurrency,
            amount = when (currentResult) {
                is ExchangeResultState.Success -> {
                    currentResult.result
                }
                else -> {
                    ""
                }
            }
        )
        getExchangeRates(currencyUi = input.value.fromCurrency)
    }

    private fun getExchangeRates(currencyUi: CurrencyUi) {
        viewModelScope.launch {
            try {
                val response = repository.getExchangeRates(currencyUi.toCurrency())
                exchangeRates = response.mapKeys {
                    it.key.toCurrencyUi()
                }
                getExchangeResult()
            } catch (e: Exception) {
                _result.value = ExchangeResultState.Error(e.message.toString())
            }
        }
    }

    fun updateInput(input: InputData) {
        _input.value = input
        getExchangeResult()
    }

    fun onChangedFromCurrency(currency: CurrencyUi) {
        _input.value = _input.value.copy(fromCurrency = currency)
        getExchangeRates(currency)
    }

    fun onChangedToCurrency(currency: CurrencyUi) {
        _input.value = _input.value.copy(toCurrency = currency)
        getExchangeResult()
    }

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
            _result.value = ExchangeResultState.Error("No rate found")
            return
        }
        val format = NumberFormat.getInstance(Locale.getDefault())
        format.maximumFractionDigits = 2
        val inputAmount = try {
            format.parse(input.amount)?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
        val result = inputAmount * rate
        _result.value = ExchangeResultState.Success(format.format(result))
    }
}
