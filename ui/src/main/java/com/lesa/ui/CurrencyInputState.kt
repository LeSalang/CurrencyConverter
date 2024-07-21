package com.lesa.ui

import com.lesa.ui_logic.ExchangeResultState

sealed class CurrencyInputState {
    data object Loading : CurrencyInputState()
    class Error(val text: String) : CurrencyInputState()
    class Success(val text: String) : CurrencyInputState()
}

internal fun ExchangeResultState.toCurrencyInputState(): CurrencyInputState {
    return when (this) {
        ExchangeResultState.Loading -> CurrencyInputState.Loading
        is ExchangeResultState.Error -> CurrencyInputState.Error(this.errorMessage)
        is ExchangeResultState.Success -> CurrencyInputState.Success(this.result)
    }
}
