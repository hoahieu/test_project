package com.hoahieu.demo.testproject.data.mapper

import com.hoahieu.demo.testproject.data.model.CryptoDataModel
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel

class CryptoDataToDomainMapper {
    fun mapToDomain(cryptoDataModel: CryptoDataModel): CurrencyDomainModel {
        return CurrencyDomainModel(
            id = cryptoDataModel.id,
            name = cryptoDataModel.name,
            code = cryptoDataModel.symbol
        )
    }
}