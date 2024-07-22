package com.lesa.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.data.ExchangeRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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
        amount = "0",
    )

    private val _input: MutableStateFlow<InputData> = MutableStateFlow(initialInput)
    val input: StateFlow<InputData>
        get() = _input

    private val _result: MutableStateFlow<ExchangeResultState> = MutableStateFlow(ExchangeResultState.Loading)
    val result: StateFlow<ExchangeResultState>
        get() = _result

    private var exchangeRates = mapOf<CurrencyUi, Double>()

    init {
        getCachedInput()
        getExchangeRates(fromCurrencyUi = input.value.fromCurrency)
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

    fun onChangedFromCurrency(fromCurrencyUi: CurrencyUi) {
        _input.value = _input.value.copy(fromCurrency = fromCurrencyUi)
        getExchangeRates(fromCurrencyUi = fromCurrencyUi)
        saveToDataStore()
    }

    fun onChangedToCurrency(currency: CurrencyUi) {
        _input.value = _input.value.copy(toCurrency = currency)
        getExchangeResult()
        saveToDataStore()
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
        getExchangeRates(fromCurrencyUi = input.value.fromCurrency)
        saveToDataStore()
    }

    private fun getCachedInput() {
        viewModelScope.launch {
            try {
                val response = repository.loadFromDataStore().first()
                _input.value = InputData(
                    fromCurrency = response.from.toCurrencyUi(),
                    toCurrency = response.to.toCurrencyUi(),
                    amount = "0"
                )
                exchangeRates = response.rates.mapKeys {
                    it.key.toCurrencyUi()
                }
            } catch (e: Exception) {
                _result.value = ExchangeResultState.Error(e.message.toString())
            }
        }
    }

    private fun getExchangeRates(
        fromCurrencyUi: CurrencyUi
    ) {
        viewModelScope.launch {
            try {
                val response = repository.getExchangeRatesFromApi(
                    fromCurrency = fromCurrencyUi.toCurrency()
                )
                exchangeRates = response.mapKeys {
                    it.key.toCurrencyUi()
                }
                getExchangeResult()
            } catch (e: Exception) {
                _result.value = ExchangeResultState.Error(e.message.toString())
            }
        }
        saveToDataStore()
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

    private fun saveToDataStore() {
        viewModelScope.launch {
            repository.saveToDataStore(
                from = input.value.fromCurrency.toCurrency(),
                to = input.value.toCurrency.toCurrency(),
                rates = exchangeRates.mapKeys {
                    it.key.toCurrency()
                }
            )
        }
    }
}
