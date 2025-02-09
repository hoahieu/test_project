package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.model.FiatDataModel

interface FiatRemoteDataSource {
    suspend fun getFiatList(): List<FiatDataModel>
}

class FiatRemoteDataSourceImpl : FiatRemoteDataSource {
    override suspend fun getFiatList(): List<FiatDataModel> {
        return sampleFiatList
    }
}

private val sampleFiatList = listOf(
    FiatDataModel("SGD", "Singapore Dollar", "$", "SGD"),
    FiatDataModel("EUR", "Euro", "€", "EUR"),
    FiatDataModel("GBP", "British Pound", "£", "GBP"),
    FiatDataModel("HKD", "Hong Kong Dollar", "$", "HKD"),
    FiatDataModel("JPY", "Japanese Yen", "¥", "JPY"),
    FiatDataModel("AUD", "Australian Dollar", "$", "AUD"),
    FiatDataModel("USD", "United States Dollar", "$", "USD")
)