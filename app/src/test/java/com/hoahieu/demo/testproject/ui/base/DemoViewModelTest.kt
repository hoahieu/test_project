package com.hoahieu.demo.testproject.ui.base

import com.hoahieu.demo.testproject.CoroutinesTestRule
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.usecase.ClearDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.GenerateDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetAllCurrenciesUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetCryptoListUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetFiatListUseCase
import com.hoahieu.demo.testproject.testDispatcherProvider
import com.hoahieu.demo.testproject.ui.mapper.CurrencyDomainToUiMapper
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataCleared
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataFetched
import com.hoahieu.demo.testproject.ui.model.DemoUiState.DataGenerated
import com.hoahieu.demo.testproject.ui.model.DemoUiState.Init
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DemoViewModelTest {
    @get: Rule
    var testRule = CoroutinesTestRule()

    private lateinit var classUnderTest: DemoViewModel
    private val clearDataUseCase: ClearDataUseCase = mock()
    private val generateDataUseCase: GenerateDataUseCase = mock()
    private val getFiatListUseCase: GetFiatListUseCase = mock()
    private val getCryptoUseCase: GetCryptoListUseCase = mock()
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase = mock()
    private val currencyDomainToUiMapper = CurrencyDomainToUiMapper()

    private val testFiatDomainModels = listOf(
        CurrencyDomainModel("11", "Sample Fiat 1", "FI1"),
        CurrencyDomainModel("12", "Sample Fiat 2", "FI2")
    )
    private val testCryptoDomainModels = listOf(
        CurrencyDomainModel("1", "Sample Crypto 1", "CR1"),
        CurrencyDomainModel("2", "Sample Crypto 2", "CR2")
    )
    private val testFiatCurrencyInfo = listOf(
        CurrencyInfo("11", "Sample Fiat 1", "FI1"),
        CurrencyInfo("12", "Sample Fiat 2", "FI2")
    )
    private val testCryptoCurrencyInfo = listOf(
        CurrencyInfo("1", "Sample Crypto 1", "CR1"),
        CurrencyInfo("2", "Sample Crypto 2", "CR2")
    )

    @Before
    fun setup() {
        runTest {
            whenever(getCryptoUseCase.execute(Unit)).thenReturn(
                Result.success(testCryptoDomainModels)
            )
            whenever(getFiatListUseCase.execute(Unit)).thenReturn(
                Result.success(testFiatDomainModels)
            )
            whenever(getAllCurrenciesUseCase.execute(Unit)).thenReturn(
                Result.success(testCryptoDomainModels.plus(testFiatDomainModels))
            )
            whenever(clearDataUseCase.execute(Unit)).thenReturn(
                Result.success(Unit)
            )
            whenever(generateDataUseCase.execute(Unit)).thenReturn(
                Result.success(Unit)
            )
        }
        classUnderTest = DemoViewModel(
            clearDataUseCase,
            generateDataUseCase,
            getFiatListUseCase,
            getCryptoUseCase,
            getAllCurrenciesUseCase,
            currencyDomainToUiMapper,
            testDispatcherProvider
        )
    }

    @Test
    fun `verify DataCleared is emitted when clearData() is called`() {
        classUnderTest.clearData()
        assertEquals(
            DataCleared,
            classUnderTest.uiState.value
        )
    }

    @Test
    fun `verify DataGenerated is emitted when generateData() is called`() {
        classUnderTest.generateData()
        assertEquals(
            DataGenerated,
            classUnderTest.uiState.value
        )
    }

    @Test
    fun `verify Init is emitted when clearState() is called`() {
        classUnderTest.clearState()
        assertEquals(
            Init,
            classUnderTest.uiState.value
        )
    }

    @Test
    fun `verify ListFetched is emitted when getFiats() is called`() {
        classUnderTest.getFiats()
        assertEquals(
            DataFetched(testFiatCurrencyInfo),
            classUnderTest.uiState.value
        )
    }

    @Test
    fun `verify ListFetched is emitted when getCryptos() is called`() {
        classUnderTest.getCryptos()
        assertEquals(
            DataFetched(testCryptoCurrencyInfo),
            classUnderTest.uiState.value
        )
    }

    @Test
    fun `verify ListFetched is emitted when getAllCurrencies() is called`() {
        classUnderTest.getAllCurrencies()
        assertEquals(
            DataFetched(testCryptoCurrencyInfo.plus(testFiatCurrencyInfo)),
            classUnderTest.uiState.value
        )
    }
}