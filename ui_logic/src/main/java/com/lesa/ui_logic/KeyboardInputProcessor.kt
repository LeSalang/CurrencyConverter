package com.lesa.ui_logic

import java.text.DecimalFormatSymbols
import javax.inject.Inject

class KeyboardInputProcessor @Inject constructor() {
    operator fun invoke(
        oldString: String,
        inputKey: KeyboardKey
    ): String {
        var newString = oldString
        val separator = DecimalFormatSymbols.getInstance().decimalSeparator
        when (inputKey) {
            is KeyboardKey.Digit -> {
                if (
                    newString.contains(separator) &&
                    newString.substringAfter(separator).length >= 2
                ) {
                    return newString
                }
                newString = if (oldString == "0") {
                    inputKey.digit.toString()
                } else {
                    oldString + inputKey.digit
                }
            }

            KeyboardKey.Delete -> {
                newString = if (oldString.length > 1) {
                    oldString.dropLast(1)
                } else {
                    "0"
                }
            }

            KeyboardKey.Separator -> {
                newString = if (oldString == "0") {
                    "0$separator"
                } else if (oldString.contains(separator)) {
                    oldString
                } else {
                    "$oldString$separator"
                }
            }
        }
        return newString
    }
}
