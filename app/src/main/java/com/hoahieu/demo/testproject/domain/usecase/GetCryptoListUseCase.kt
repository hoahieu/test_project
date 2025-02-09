package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

interface GetCryptoListUseCase : UseCase<Unit, List<CurrencyDomainModel>>

class GetCryptoListUseCaseImpl(
    private val repository: CurrencyRepository
) : GetCryptoListUseCase {
    override suspend fun execute(parameter: Unit): Result<List<CurrencyDomainModel>> {
        return runCatching {
            repository.getCryptoList()
        }
    }
}