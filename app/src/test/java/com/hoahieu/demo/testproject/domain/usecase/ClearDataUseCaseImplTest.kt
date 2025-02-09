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

class ClearDataUseCaseImplTest {
    private lateinit var classUnderTest: ClearDataUseCaseImpl

    private val currencyRepository: CurrencyRepository = mock()

    @Before
    fun setup() {
        runBlocking {
            whenever(currencyRepository.clearData()).thenReturn(Unit)
            classUnderTest = ClearDataUseCaseImpl(currencyRepository)
        }
    }

    @Test
    fun `verify currencyRepository clearData() is called`() {
        runTest {
            classUnderTest.execute(Unit)
            verify(currencyRepository, times(1)).clearData()
        }
    }
}