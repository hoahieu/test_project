package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.database.FiatDao
import com.hoahieu.demo.testproject.data.model.FiatDataModel

interface FiatLocalDataSource {
    suspend fun getAll(): List<FiatDataModel>
    suspend fun clear()
    suspend fun save(fiats: List<FiatDataModel>)
}

class FiatLocalDataSourceImpl(
    private val fiatDao: FiatDao
) : FiatLocalDataSource {
    override suspend fun getAll(): List<FiatDataModel> = fiatDao.getAll()
    override suspend fun clear() = fiatDao.deleteAll()
    override suspend fun save(fiats: List<FiatDataModel>) {
        fiatDao.insertAll(fiats)
    }
}