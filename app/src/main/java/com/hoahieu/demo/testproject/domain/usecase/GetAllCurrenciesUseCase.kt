package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

interface GetAllCurrenciesUseCase : UseCase<Unit, List<CurrencyDomainModel>>

class GetAllCurrenciesUseCaseImpl(
    private val repository: CurrencyRepository
) : GetAllCurrenciesUseCase {
    override suspend fun execute(parameter: Unit): Result<List<CurrencyDomainModel>> {
        return runCatching {
            val cryptos = repository.getCryptoList()
            val fiats = repository.getFiatList()
            cryptos.plus(fiats)
        }
    }
}