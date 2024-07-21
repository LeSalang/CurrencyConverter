package com.lesa.ui_logic

import org.junit.Assert.assertEquals
import org.junit.Test

class KeyboardInputProcessorTest {

    private val processor = KeyboardInputProcessor()

    @Test
    fun `input digit when there is empty field`() {
        // given
        val oldString = ""
        val key = KeyboardKey.Digit(1)
        val expected = "1"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input digit when there is a number`() {
        // given
        val oldString = "1"
        val key = KeyboardKey.Digit(1)
        val expected = "11"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input digit when there is a number with separator in the end`() {
        // given
        val oldString = "1."
        val key = KeyboardKey.Digit(1)
        val expected = "1.1"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input digit when there is a number with separator and 2 digits after it`() {
        // given
        val oldString = "1.00"
        val key = KeyboardKey.Digit(1)
        val expected = "1.00"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input digit when there is zero`() {
        // given
        val oldString = "0"
        val key = KeyboardKey.Digit(1)
        val expected = "0.1"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input delete when there is a single digit`() {
        // given
        val oldString = "1"
        val key = KeyboardKey.Delete
        val expected = ""
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input delete when there is a two-digit number`() {
        // given
        val oldString = "11"
        val key = KeyboardKey.Delete
        val expected = "1"
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input separator when there is empty string`() {
        // given
        val oldString = ""
        val key = KeyboardKey.Separator
        val expected = "0."
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input separator when there is a number`() {
        // given
        val oldString = "1"
        val key = KeyboardKey.Separator
        val expected = "1."
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }

    @Test
    fun `input separator when there is a number with separator in the end`() {
        // given
        val oldString = "1."
        val key = KeyboardKey.Separator
        val expected = "1."
        // when
        val result = processor.invoke(oldString, key)
        // then
        assertEquals(expected, result)
    }
}
