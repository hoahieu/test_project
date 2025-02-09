package com.hoahieu.demo.testproject.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hoahieu.demo.testproject.data.model.CryptoDataModel
import com.hoahieu.demo.testproject.data.model.FiatDataModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var fiatDao: FiatDao
    private lateinit var cryptoDao: CryptoDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        fiatDao = database.fiatDao()
        cryptoDao = database.cryptoDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    private fun generateFiats() = List(5) { index ->
        FiatDataModel(
            id = "$index",
            name = "Data $index",
            code = "DAT$index",
            symbol = "D$index"
        )
    }

    private fun generateCryptos() = List(9) { index ->
        CryptoDataModel(
            id = "CR$index",
            name = "Crypto Number $index",
            symbol = "C$index"
        )
    }

    @Test
    fun writeFiatsThenRead() {
        val input = generateFiats().shuffled()
        fiatDao.insertAll(input)
        val output = fiatDao.getAll()
        assertEquals(input, output)
    }

    @Test
    fun writeFiatsThenDelete() {
        val input = generateFiats()
        fiatDao.insertAll(input)
        assertEquals(input.size, fiatDao.getAll().size)
        fiatDao.deleteAll()
        assertEquals(0, fiatDao.getAll().size)
    }

    @Test
    fun writeCryptosThenRead() {
        val input = generateCryptos().shuffled()
        cryptoDao.insertAll(input)
        val output = cryptoDao.getAll()
        assertEquals(input, output)
    }

    @Test
    fun writeCryptosThenDelete() {
        val data = generateCryptos()
        cryptoDao.insertAll(data)
        assertEquals(data.size, cryptoDao.getAll().size)
        cryptoDao.deleteAll()
        assertEquals(0, cryptoDao.getAll().size)
    }
}