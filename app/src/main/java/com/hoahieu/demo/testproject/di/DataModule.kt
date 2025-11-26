package com.hoahieu.demo.testproject.di

import android.content.Context
import com.hoahieu.demo.testproject.data.CurrencyRepositoryImpl
import com.hoahieu.demo.testproject.data.database.AppDatabase
import com.hoahieu.demo.testproject.data.database.createRoomDatabase
import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSourceImpl
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSourceImpl
import com.hoahieu.demo.testproject.data.mapper.CryptoDataToDomainMapper
import com.hoahieu.demo.testproject.data.mapper.FiatDataToDomainMapper
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule =
    module {
        single { provideRoomDatabase(androidContext()) }
        singleOf(::provideCryptoDao)
        singleOf(::provideFiatDao)
        singleOf(::CryptoLocalDataSourceImpl) bind CryptoLocalDataSource::class
        singleOf(::FiatLocalDataSourceImpl) bind FiatLocalDataSource::class
        singleOf(::CryptoDataToDomainMapper)
        singleOf(::FiatDataToDomainMapper)
        singleOf(::CurrencyRepositoryImpl) bind CurrencyRepository::class
    }

fun provideRoomDatabase(context: Context) = createRoomDatabase(context)

fun provideCryptoDao(appDatabase: AppDatabase) = appDatabase.cryptoDao()

fun provideFiatDao(appDatabase: AppDatabase) = appDatabase.fiatDao()
