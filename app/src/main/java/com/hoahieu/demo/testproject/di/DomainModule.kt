package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.domain.usecase.ClearDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.ClearDataUseCaseImpl
import com.hoahieu.demo.testproject.domain.usecase.GenerateDataUseCase
import com.hoahieu.demo.testproject.domain.usecase.GenerateDataUseCaseImpl
import com.hoahieu.demo.testproject.domain.usecase.GetAllCurrenciesUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetAllCurrenciesUseCaseImpl
import com.hoahieu.demo.testproject.domain.usecase.GetCryptoListUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetCryptoListUseCaseImpl
import com.hoahieu.demo.testproject.domain.usecase.GetFiatListUseCase
import com.hoahieu.demo.testproject.domain.usecase.GetFiatListUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule =
    module {
        singleOf(::ClearDataUseCaseImpl) bind ClearDataUseCase::class
        singleOf(::GenerateDataUseCaseImpl) bind GenerateDataUseCase::class
        singleOf(::GetAllCurrenciesUseCaseImpl) bind GetAllCurrenciesUseCase::class
        singleOf(::GetCryptoListUseCaseImpl) bind GetCryptoListUseCase::class
        singleOf(::GetFiatListUseCaseImpl) bind GetFiatListUseCase::class
    }
