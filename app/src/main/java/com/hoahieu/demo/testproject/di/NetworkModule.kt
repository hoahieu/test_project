package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSourceImpl
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule =
    module {
        singleOf(::CryptoRemoteDataSourceImpl) bind CryptoRemoteDataSource::class
        singleOf(::FiatRemoteDataSourceImpl) bind FiatRemoteDataSource::class
    }
