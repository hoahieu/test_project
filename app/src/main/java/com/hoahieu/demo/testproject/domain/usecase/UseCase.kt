package com.hoahieu.demo.testproject.domain.usecase

interface UseCase<In, Out> {
    suspend fun execute(parameter: In): Result<Out>
}