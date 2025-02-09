package com.hoahieu.demo.testproject.ui

import com.hoahieu.demo.testproject.ui.model.CurrencyInfo
import org.junit.Test
import kotlin.test.assertEquals

class CurrencyInfoFilterTest {
    private val classUnderTest = CurrencyInfoFilter()

    @Test
    fun `requirement 1_1 test`() {
        val input = listOf(
            CurrencyInfo(id = "1", name = "Foobar", "FBR"),
            CurrencyInfo(id = "2", name = "Barfoo", "BRF"),
            CurrencyInfo(id = "3", name = "Ethereum", "ETH"),
            CurrencyInfo(id = "4", name = "Ethereum Classic", "ETC"),
            CurrencyInfo(id = "5", name = "Bar foo", "BSF")
        )
        val expectedOutput = listOf(
            input[0]
        )
        val actualOutput = classUnderTest.filter(input, "foo")
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `requirement 1_2 test`() {
        val input = listOf(
            CurrencyInfo(id = "1", name = "Foobar", "FBR"),
            CurrencyInfo(id = "2", name = "Barfoo", "BRF"),
            CurrencyInfo(id = "3", name = "Ethereum", "ETH"),
            CurrencyInfo(id = "4", name = "Ethereum Classic", "ETC"),
            CurrencyInfo(id = "5", name = "Fake Ethereum", "ETF")
        )
        val expectedOutput = listOf(
            input[2], input[3]
        )
        val actualOutput = classUnderTest.filter(input, "Ethe")
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `requirement 2_1 test`() {
        val input = listOf(
            CurrencyInfo(id = "1", name = "Ethereum", "ETH"),
            CurrencyInfo(id = "2", name = "Ethereum Classic", "ETC"),
            CurrencyInfo(id = "3", name = "Tronclassic", "TCL")
        )
        val expectedOutput = listOf(
            input[1]
        )
        val actualOutput = classUnderTest.filter(input, " classic")
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `requirement 3 test`() {
        val input = listOf(
            CurrencyInfo(id = "1", name = "Random name 1", "ETH"),
            CurrencyInfo(id = "2", name = "Random name 2", "ETC"),
            CurrencyInfo(id = "3", name = "Random name 3", "BET"),
            CurrencyInfo(id = "4", name = "Random name 4", "ETN")
        )
        val expectedOutput = listOf(
            input[0], input[1], input[3]
        )
        val actualOutput = classUnderTest.filter(input, "ET")
        assertEquals(expectedOutput, actualOutput)
    }
}