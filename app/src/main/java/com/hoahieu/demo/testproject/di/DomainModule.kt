package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository
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
import org.koin.dsl.module

val domainModule = module {
    single { provideClearDataUseCase(get()) }
    single { provideGenerateDataUseCase(get()) }
    single { provideGetAllCurrenciesUseCase(get()) }
    single { provideGetCryptoListUseCase(get()) }
    single { provideGetFiatListUseCase(get()) }
}

fun provideClearDataUseCase(repository: CurrencyRepository): ClearDataUseCase =
    ClearDataUseCaseImpl(repository)

fun provideGenerateDataUseCase(repository: CurrencyRepository): GenerateDataUseCase =
    GenerateDataUseCaseImpl(repository)

fun provideGetAllCurrenciesUseCase(repository: CurrencyRepository): GetAllCurrenciesUseCase =
    GetAllCurrenciesUseCaseImpl(repository)

fun provideGetCryptoListUseCase(repository: CurrencyRepository): GetCryptoListUseCase =
    GetCryptoListUseCaseImpl(repository)

fun provideGetFiatListUseCase(repository: CurrencyRepository): GetFiatListUseCase =
    GetFiatListUseCaseImpl(repository)