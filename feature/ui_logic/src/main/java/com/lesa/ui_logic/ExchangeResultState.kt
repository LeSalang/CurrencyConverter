package com.lesa.ui_logic

sealed class ExchangeResultState {
    data object Loading : ExchangeResultState()
    data class Success(val result: String) : ExchangeResultState()
    data class Error(val errorMessage: String) : ExchangeResultState()
}
