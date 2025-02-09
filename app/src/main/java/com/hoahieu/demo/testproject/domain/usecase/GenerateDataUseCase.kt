package com.hoahieu.demo.testproject.domain.usecase

import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository

interface GenerateDataUseCase : UseCase<Unit, Unit>

class GenerateDataUseCaseImpl(
    private val currencyRepository: CurrencyRepository
) : GenerateDataUseCase {
    override suspend fun execute(parameter: Unit): Result<Unit> {
        return runCatching {
            currencyRepository.getData()
        }
    }
}