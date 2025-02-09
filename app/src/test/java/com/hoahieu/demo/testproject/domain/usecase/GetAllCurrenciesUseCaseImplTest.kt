package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GetAllCurrenciesUseCaseImplTest {
    private lateinit var classUnderTest: GetAllCurrenciesUseCaseImpl

    private val currencyRepository: CurrencyRepository = mock()

    private val sampleCryptos = listOf(
        CurrencyDomainModel("1", "CRYP number 1", "CR1"),
        CurrencyDomainModel("2", "CRYP number 2", "CR2"),
        CurrencyDomainModel("3", "CRYP number 3", "CR3"),
        CurrencyDomainModel("4", "CRYP number 4", "CR4"),
        CurrencyDomainModel("5", "CRYP number 5", "CR5")
    )

    private val sampleFiats = listOf(
        CurrencyDomainModel("1", "CRYP number 1", "CR1"),
        CurrencyDomainModel("2", "CRYP number 2", "CR2"),
        CurrencyDomainModel("3", "CRYP number 3", "CR3"),
        CurrencyDomainModel("4", "CRYP number 4", "CR4"),
        CurrencyDomainModel("5", "CRYP number 5", "CR5")
    )

    @Before
    fun setup() {
        runBlocking {
            whenever(currencyRepository.getCryptoList()).thenReturn(sampleCryptos)
            whenever(currencyRepository.getFiatList()).thenReturn(sampleFiats)
            classUnderTest = GetAllCurrenciesUseCaseImpl(currencyRepository)
        }
    }

    @Test
    fun `verify data is returned`() {
        runTest {
            val actualOutput = classUnderTest.execute(Unit)
            assertEquals(Result.success(sampleCryptos.plus(sampleFiats)), actualOutput)
        }
    }
}