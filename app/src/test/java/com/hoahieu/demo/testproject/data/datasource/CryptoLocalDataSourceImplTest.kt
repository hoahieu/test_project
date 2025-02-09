package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.database.CryptoDao
import com.hoahieu.demo.testproject.data.model.CryptoDataModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CryptoLocalDataSourceImplTest {
    lateinit var classUnderTest: CryptoLocalDataSourceImpl
    private val cryptoDao: CryptoDao = mock()

    @Before
    fun setup() {
        classUnderTest = CryptoLocalDataSourceImpl(cryptoDao)
    }

    @Test
    fun `check cryptoDao getAll is called`() {
        runTest {
            classUnderTest.getAll()
        }
        verify(cryptoDao, times(1)).getAll()
    }

    @Test
    fun `check cryptoDao deleteAll is called`() {
        runTest {
            classUnderTest.clear()
        }
        verify(cryptoDao, times(1)).deleteAll()
    }

    @Test
    fun `check cryptoDao insertAll is called`() {
        val input = listOf(
            CryptoDataModel(
                "A", "B", "C"
            )
        )
        runTest {
            classUnderTest.save(input)
        }
        verify(cryptoDao, times(1)).insertAll(input)
    }
}