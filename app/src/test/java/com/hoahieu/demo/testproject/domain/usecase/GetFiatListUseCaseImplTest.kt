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

class GetFiatListUseCaseImplTest {
    private lateinit var classUnderTest: GetFiatListUseCaseImpl

    private val currencyRepository: CurrencyRepository = mock()

    private val sampleData = listOf(
        CurrencyDomainModel("1", "FIAT number 1", "FI1"),
        CurrencyDomainModel("2", "FIAT number 2", "FI2"),
        CurrencyDomainModel("3", "FIAT number 3", "FI3"),
        CurrencyDomainModel("4", "FIAT number 4", "FI4"),
        CurrencyDomainModel("5", "FIAT number 5", "FI5")
    )

    @Before
    fun setup() {
        runBlocking {
            whenever(currencyRepository.getFiatList()).thenReturn(sampleData)
            classUnderTest = GetFiatListUseCaseImpl(currencyRepository)
        }
    }

    @Test
    fun `verify data is returned`() {
        runTest {
            val actualOutput = classUnderTest.execute(Unit)
            assertEquals(Result.success(sampleData), actualOutput)
        }
    }
}