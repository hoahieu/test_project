package com.hoahieu.demo.testproject.domain.repository

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel

interface CurrencyRepository {
    suspend fun clearData()
    suspend fun getFiatList(): List<CurrencyDomainModel>
    suspend fun getCryptoList(): List<CurrencyDomainModel>
    suspend fun getData()
}