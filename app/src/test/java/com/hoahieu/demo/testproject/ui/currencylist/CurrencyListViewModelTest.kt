package com.hoahieu.demo.testproject.ui.currencylist

import com.hoahieu.demo.testproject.CoroutinesTestRule
import com.hoahieu.demo.testproject.testDispatcherProvider
import com.hoahieu.demo.testproject.ui.CurrencyInfoFilter
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyListViewModelTest {
    @get: Rule
    var testRule = CoroutinesTestRule()

    private lateinit var classUnderTest: CurrencyListViewModel
    private val currencyInfoFilter = CurrencyInfoFilter()

    @Before
    fun setup() {
        classUnderTest = CurrencyListViewModel(
            currencyInfoFilter,
            testDispatcherProvider
        )
    }

    private val testData = listOf(
        CurrencyInfo("1", "Foobar", "FBR"),
        CurrencyInfo("2", "Barfoo", "BRF"),
        CurrencyInfo("3", "Etherum Classic", "ETC"),
        CurrencyInfo("4", "Coinclassic", "CET")
    )

    @Test
    fun `set initial data and check the result`() {
        classUnderTest.setCurrencyList(testData)
        assertEquals(
            testData,
            classUnderTest.result.value
        )
    }

    @Test
    fun `search for a term and check the result`() {
        classUnderTest.setCurrencyList(testData)
        classUnderTest.search("Foo")
        val expectedOutput = listOf(CurrencyInfo("1", "Foobar", "FBR"))
        assertEquals(
            expectedOutput,
            classUnderTest.result.value
        )
    }
}