package com.hoahieu.demo.testproject.data

import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSource
import com.hoahieu.demo.testproject.data.mapper.CryptoDataToDomainMapper
import com.hoahieu.demo.testproject.data.mapper.FiatDataToDomainMapper
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val cryptoLocalDataSource: CryptoLocalDataSource,
    private val fiatLocalDataSource: FiatLocalDataSource,
    private val cryptoDataToDomainMapper: CryptoDataToDomainMapper,
    private val fiatDataToDomainMapper: FiatDataToDomainMapper,
    private val cryptoRemoteDataSource: CryptoRemoteDataSource,
    private val fiatRemoteDataSource: FiatRemoteDataSource
) : CurrencyRepository {
    override suspend fun clearData() {
        cryptoLocalDataSource.clear()
        fiatLocalDataSource.clear()
    }

    override suspend fun getFiatList(): List<CurrencyDomainModel> {
        return fiatLocalDataSource.getAll().map(fiatDataToDomainMapper::mapToDomain)
    }

    override suspend fun getCryptoList(): List<CurrencyDomainModel> {
        return cryptoLocalDataSource.getAll().map(cryptoDataToDomainMapper::mapToDomain)
    }

    override suspend fun getData() {
        clearData()
        val cryptoList = cryptoRemoteDataSource.getCryptoList()
        val fiatList = fiatRemoteDataSource.getFiatList()
        cryptoLocalDataSource.save(cryptoList)
        fiatLocalDataSource.save(fiatList)
    }
}