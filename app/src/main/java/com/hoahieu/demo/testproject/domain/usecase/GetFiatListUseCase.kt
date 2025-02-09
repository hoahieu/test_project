package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.model.CurrencyDomainModel
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

interface GetFiatListUseCase : UseCase<Unit, List<CurrencyDomainModel>>

class GetFiatListUseCaseImpl(
    private val repository: CurrencyRepository
) : GetFiatListUseCase {
    override suspend fun execute(parameter: Unit): Result<List<CurrencyDomainModel>> {
        return runCatching {
            repository.getFiatList()
        }
    }
}