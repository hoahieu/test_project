package com.hoahieu.demo.testproject.ui.mapper

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.ui.model.CurrencyInfo

class CurrencyDomainToUiMapper {
    fun mapToUi(currencyDomainModel: CurrencyDomainModel) = CurrencyInfo(
        id = currencyDomainModel.id,
        name = currencyDomainModel.name,
        code = currencyDomainModel.code
    )
}