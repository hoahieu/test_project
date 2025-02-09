package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSourceImpl
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSourceImpl
import org.koin.dsl.module

val networkModule = module {
    single { provideCryptoRemoteDataSource() }
    single { provideFiatRemoteDataSource() }
}

fun provideCryptoRemoteDataSource(): CryptoRemoteDataSource = CryptoRemoteDataSourceImpl()
fun provideFiatRemoteDataSource(): FiatRemoteDataSource = FiatRemoteDataSourceImpl()