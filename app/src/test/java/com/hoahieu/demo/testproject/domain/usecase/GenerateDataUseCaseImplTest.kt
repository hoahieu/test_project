package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenerateDataUseCaseImplTest {
    private lateinit var classUnderTest: GenerateDataUseCaseImpl

    private val currencyRepository: CurrencyRepository = mock()

    @Before
    fun setup() {
        runBlocking {
            whenever(currencyRepository.getData()).thenReturn(Unit)
            classUnderTest = GenerateDataUseCaseImpl(currencyRepository)
        }
    }

    @Test
    fun `verify currencyRepository getData() is called`() {
        runTest {
            classUnderTest.execute(Unit)
            verify(currencyRepository, times(1)).getData()
        }
    }
}