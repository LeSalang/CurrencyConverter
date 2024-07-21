package com.lesa.ui_logic

import javax.inject.Inject

class KeyboardInputProcessor @Inject constructor() {
    operator fun invoke(
        oldString: String,
        inputKey: KeyboardKey
    ): String {
        var newString = oldString
        when (inputKey) {
            is KeyboardKey.Digit -> {
                if (newString.contains('.') && newString.substringAfter('.').length >= 2) {
                    return newString
                }
                newString = if (oldString == "0") {
                    oldString + "." + inputKey.digit
                } else {
                    oldString + inputKey.digit
                }
            }

            KeyboardKey.Delete -> {
                newString = oldString.dropLast(1)
            }

            KeyboardKey.Separator -> {
                newString = if (oldString.isEmpty()) {
                    "0."
                } else if (oldString.contains(".")) {
                    oldString
                } else {
                    "$oldString."
                }
            }
        }
        return newString
    }
}
