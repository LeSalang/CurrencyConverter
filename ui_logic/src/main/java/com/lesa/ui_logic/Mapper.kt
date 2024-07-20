package com.lesa.ui_logic

import com.lesa.data.Currency

internal fun Currency.toCurrencyUi(): CurrencyUi {
    return CurrencyUi.entries.first { currencyUi ->
        currencyUi.name == this.name
    }
}

internal fun CurrencyUi.toCurrency(): Currency {
    return Currency.valueOf(this.name)
}
