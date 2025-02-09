package com.hoahieu.demo.testproject.data.datasource

import com.hoahieu.demo.testproject.data.model.CryptoDataModel

interface CryptoRemoteDataSource {
    suspend fun getCryptoList(): List<CryptoDataModel>
}

class CryptoRemoteDataSourceImpl : CryptoRemoteDataSource {
    override suspend fun getCryptoList(): List<CryptoDataModel> {
        return sampleCryptoList
    }
}

private val sampleCryptoList = listOf(
    CryptoDataModel("BTC", "Bitcoin", "BTC"),
    CryptoDataModel("ETH", "Ethereum", "ETH"),
    CryptoDataModel("XRP", "XRP", "XRP"),
    CryptoDataModel("BCH", "Bitcoin Cash", "BCH"),
    CryptoDataModel("LTC", "Litecoin", "LTC"),
    CryptoDataModel("EOS", "EOS", "EOS"),
    CryptoDataModel("BNB", "Binance Coin", "BNB"),
    CryptoDataModel("LINK", "Chainlink", "LINK"),
    CryptoDataModel("NEO", "NEO", "NEO"),
    CryptoDataModel("ETC", "Ethereum Classic", "ETC"),
    CryptoDataModel("ONT", "Ontology", "ONT"),
    CryptoDataModel("CRO", "Crypto.com Chain", "CRO"),
    CryptoDataModel("CUC", "Cucumber", "CUC"),
    CryptoDataModel("USDC", "USD Coin", "USDC")
)
