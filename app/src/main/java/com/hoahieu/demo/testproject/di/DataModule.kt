package com.hoahieu.demo.testproject.di

import android.content.Context
import com.hoahieu.demo.testproject.data.CurrencyRepositoryImpl
import com.hoahieu.demo.testproject.data.database.AppDatabase
import com.hoahieu.demo.testproject.data.database.CryptoDao
import com.hoahieu.demo.testproject.data.database.FiatDao
import com.hoahieu.demo.testproject.data.database.createRoomDatabase
import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.CryptoLocalDataSourceImpl
import com.hoahieu.demo.testproject.data.datasource.CryptoRemoteDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSource
import com.hoahieu.demo.testproject.data.datasource.FiatLocalDataSourceImpl
import com.hoahieu.demo.testproject.data.datasource.FiatRemoteDataSource
import com.hoahieu.demo.testproject.data.mapper.CryptoDataToDomainMapper
import com.hoahieu.demo.testproject.data.mapper.FiatDataToDomainMapper
import com.hoahieu.demo.testproject.domain.repository.CurrencyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { provideCryptoDao(get()) }
    single { provideFiatDao(get()) }
    single { provideCryptoLocalDataSource(get()) }
    single { provideFiatLocalDataSource(get()) }
    single { provideCryptoDataToDomainMapper() }
    single { provideFiatDataToDomainMapper() }
    single { provideCurrencyRepository(get(), get(), get(), get(), get(), get()) }
}

fun provideRoomDatabase(context: Context) = createRoomDatabase(context)

fun provideCryptoDao(appDatabase: AppDatabase) = appDatabase.cryptoDao()

fun provideFiatDao(appDatabase: AppDatabase) = appDatabase.fiatDao()

fun provideCryptoLocalDataSource(cryptoDao: CryptoDao): CryptoLocalDataSource =
    CryptoLocalDataSourceImpl(cryptoDao)

fun provideFiatLocalDataSource(fiatDao: FiatDao): FiatLocalDataSource =
    FiatLocalDataSourceImpl(fiatDao)

fun provideCryptoDataToDomainMapper() = CryptoDataToDomainMapper()
fun provideFiatDataToDomainMapper() = FiatDataToDomainMapper()

fun provideCurrencyRepository(
    cryptoDataSource: CryptoLocalDataSource,
    fiatLocalDataSource: FiatLocalDataSource,
    cryptoDataToDomainMapper: CryptoDataToDomainMapper,
    fiatDataToDomainMapper: FiatDataToDomainMapper,
    cryptoRemoteDataSource: CryptoRemoteDataSource,
    fiatRemoteDataSource: FiatRemoteDataSource
): CurrencyRepository = CurrencyRepositoryImpl(
    cryptoDataSource,
    fiatLocalDataSource,
    cryptoDataToDomainMapper,
    fiatDataToDomainMapper,
    cryptoRemoteDataSource,
    fiatRemoteDataSource
)