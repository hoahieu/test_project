package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

interface ClearDataUseCase : UseCase<Unit, Unit>

class ClearDataUseCaseImpl(
    private val currencyRepository: CurrencyRepository
) : ClearDataUseCase {
    override suspend fun execute(parameter: Unit): Result<Unit> {
        return runCatching {
            currencyRepository.clearData()
        }
    }
}