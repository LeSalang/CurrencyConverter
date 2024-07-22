package com.lesa.ui_logic

sealed class KeyboardKey {
    class Digit(val digit: Int) : KeyboardKey()
    data object Delete : KeyboardKey()
    data object Separator : KeyboardKey()
}

val keyboardKeys: List<List<KeyboardKey>> = listOf(
    listOf(KeyboardKey.Digit(1), KeyboardKey.Digit(2), KeyboardKey.Digit(3),),
    listOf(KeyboardKey.Digit(4), KeyboardKey.Digit(5), KeyboardKey.Digit(6),),
    listOf(KeyboardKey.Digit(7), KeyboardKey.Digit(8), KeyboardKey.Digit(9),),
    listOf(KeyboardKey.Separator, KeyboardKey.Digit(0), KeyboardKey.Delete),
)
