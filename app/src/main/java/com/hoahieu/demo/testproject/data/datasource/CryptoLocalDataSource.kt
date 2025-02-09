package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.database.CryptoDao
import com.hoahieu.demo.testproject.data.model.CryptoDataModel

interface CryptoLocalDataSource {
    suspend fun getAll(): List<CryptoDataModel>
    suspend fun clear()
    suspend fun save(cryptos: List<CryptoDataModel>)
}

class CryptoLocalDataSourceImpl(
    private val cryptoDao: CryptoDao
) : CryptoLocalDataSource {
    override suspend fun getAll(): List<CryptoDataModel> = cryptoDao.getAll()
    override suspend fun clear() = cryptoDao.deleteAll()
    override suspend fun save(cryptos: List<CryptoDataModel>) {
        cryptoDao.insertAll(cryptos)
    }
}