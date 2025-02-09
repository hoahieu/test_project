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

class GetCryptoListUseCaseImplTest {
    private lateinit var classUnderTest: GetCryptoListUseCaseImpl

    private val currencyRepository: CurrencyRepository = mock()

    private val sampleData = listOf(
        CurrencyDomainModel("1", "CRYP number 1", "CR1"),
        CurrencyDomainModel("2", "CRYP number 2", "CR2"),
        CurrencyDomainModel("3", "CRYP number 3", "CR3"),
        CurrencyDomainModel("4", "CRYP number 4", "CR4"),
        CurrencyDomainModel("5", "CRYP number 5", "CR5")
    )

    @Before
    fun setup() {
        runBlocking {
            whenever(currencyRepository.getCryptoList()).thenReturn(sampleData)
            classUnderTest = GetCryptoListUseCaseImpl(currencyRepository)
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