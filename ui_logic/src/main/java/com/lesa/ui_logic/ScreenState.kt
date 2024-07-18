package com.lesa.ui_logic

sealed class ScreenState<out T> {
    data object Loading : ScreenState<Nothing>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error(val errorMessage: String) : ScreenState<Nothing>()
}
