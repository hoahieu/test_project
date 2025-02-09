package com.hoahieu.demo.testproject.data

import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSource
import com.hoahieu.demo.testproject.data.mapper.CryptoDataToDomainMapper
import com.hoahieu.demo.testproject.data.mapper.FiatDataToDomainMapper
import com.hoahieu.demo.testproject.data.model.CryptoDataModel
import com.hoahieu.demo.testproject.data.model.FiatDataModel
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CurrencyRepositoryImplTest {
    private lateinit var classUnderTest: CurrencyRepositoryImpl
    private val cryptoLocalDataSource: CryptoLocalDataSource = mock()
    private val fiatLocalDataSource: FiatLocalDataSource = mock()
    private val cryptoDataToDomainMapper = CryptoDataToDomainMapper()
    private val fiatDataToDomainMapper = FiatDataToDomainMapper()
    private val cryptoRemoteDataSource: CryptoRemoteDataSource = mock()
    private val fiatRemoteDataSource: FiatRemoteDataSource = mock()

    private val sampleCryptoList = listOf(
        CryptoDataModel("1", "CRYP number 1", "CR1"),
        CryptoDataModel("2", "CRYP number 2", "CR2"),
        CryptoDataModel("3", "CRYP number 3", "CR3"),
        CryptoDataModel("4", "CRYP number 4", "CR4"),
        CryptoDataModel("5", "CRYP number 5", "CR5")
    )

    private val sampleFiatList = listOf(
        FiatDataModel("1", "FIAT number 1", "F1", "FI1"),
        FiatDataModel("2", "FIAT number 2", "F2", "FI2"),
        FiatDataModel("3", "FIAT number 3", "F3", "FI3"),
        FiatDataModel("4", "FIAT number 4", "F4", "FI4"),
        FiatDataModel("5", "FIAT number 5", "F5", "FI5")
    )

    @Before
    fun setup() {
        runBlocking {
            whenever(fiatLocalDataSource.getAll()).thenReturn(sampleFiatList)
            whenever(cryptoLocalDataSource.getAll()).thenReturn(sampleCryptoList)
            whenever(cryptoRemoteDataSource.getCryptoList()).thenReturn(sampleCryptoList)
            whenever(fiatRemoteDataSource.getFiatList()).thenReturn(sampleFiatList)
            classUnderTest = CurrencyRepositoryImpl(
                cryptoLocalDataSource,
                fiatLocalDataSource,
                cryptoDataToDomainMapper,
                fiatDataToDomainMapper,
                cryptoRemoteDataSource,
                fiatRemoteDataSource
            )
        }
    }

    @Test
    fun `verify DataSources clear() is called`() {
        runTest {
            classUnderTest.clearData()
            verify(cryptoLocalDataSource, times(1)).clear()
            verify(fiatLocalDataSource, times(1)).clear()
        }
    }

    @Test
    fun `verify getFiatList() returns correct data`() {
        runTest {
            val expectedOutput = listOf(
                CurrencyDomainModel("1", "FIAT number 1", "FI1"),
                CurrencyDomainModel("2", "FIAT number 2", "FI2"),
                CurrencyDomainModel("3", "FIAT number 3", "FI3"),
                CurrencyDomainModel("4", "FIAT number 4", "FI4"),
                CurrencyDomainModel("5", "FIAT number 5", "FI5")
            )
            val actualOutput = classUnderTest.getFiatList()
            assertEquals(expectedOutput, actualOutput)
        }
    }

    @Test
    fun `verify getCryptoList() returns correct data`() {
        runTest {
            val expectedOutput = listOf(
                CurrencyDomainModel("1", "CRYP number 1", "CR1"),
                CurrencyDomainModel("2", "CRYP number 2", "CR2"),
                CurrencyDomainModel("3", "CRYP number 3", "CR3"),
                CurrencyDomainModel("4", "CRYP number 4", "CR4"),
                CurrencyDomainModel("5", "CRYP number 5", "CR5")
            )
            val actualOutput = classUnderTest.getCryptoList()
            assertEquals(expectedOutput, actualOutput)
        }
    }

    @Test
    fun `verify database is cleared and then inserted when getData`() {
        runTest {
            classUnderTest.getData()
            verify(cryptoLocalDataSource, times(1)).clear()
            verify(fiatLocalDataSource, times(1)).clear()
            verify(cryptoRemoteDataSource, times(1)).getCryptoList()
            verify(fiatRemoteDataSource, times(1)).getFiatList()
            verify(cryptoLocalDataSource, times(1)).save(any())
            verify(fiatLocalDataSource, times(1)).save(any())
        }
    }
}