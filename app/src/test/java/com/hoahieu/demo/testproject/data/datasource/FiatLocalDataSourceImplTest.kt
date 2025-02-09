package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.database.FiatDao
import com.hoahieu.demo.testproject.data.model.FiatDataModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FiatLocalDataSourceImplTest {
    lateinit var classUnderTest: FiatLocalDataSourceImpl
    private val fiatDao: FiatDao = mock()

    @Before
    fun setup() {
        classUnderTest = FiatLocalDataSourceImpl(fiatDao)
    }

    @Test
    fun `check fiatDao getAll is called`() {
        runTest {
            classUnderTest.getAll()
        }
        verify(fiatDao, times(1)).getAll()
    }

    @Test
    fun `check fiatDao deleteAll is called`() {
        runTest {
            classUnderTest.clear()
        }
        verify(fiatDao, times(1)).deleteAll()
    }

    @Test
    fun `check fiatDao insertAll is called`() {
        val input = listOf(
            FiatDataModel(
                "A", "B", "C", "D"
            )
        )
        runTest {
            classUnderTest.save(input)
        }
        verify(fiatDao, times(1)).insertAll(input)
    }
}