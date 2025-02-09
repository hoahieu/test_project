package com.hoahieu.demo.testproject.data.mapper

import com.hoahieu.demo.testproject.data.model.FiatDataModel
import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel

class FiatDataToDomainMapper {
    fun mapToDomain(fiatDataModel: FiatDataModel): CurrencyDomainModel {
        return CurrencyDomainModel(
            id = fiatDataModel.id,
            name = fiatDataModel.name,
            code = fiatDataModel.code
        )
    }
}