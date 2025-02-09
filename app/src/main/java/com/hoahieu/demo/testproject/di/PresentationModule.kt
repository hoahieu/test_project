package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.ui.CurrencyInfoFilter
import com.hoahieu.demo.testproject.ui.androidDispatcherProvider
import com.hoahieu.demo.testproject.ui.base.DemoViewModel
import com.hoahieu.demo.testproject.ui.currencylist.CurrencyListViewModel
import com.hoahieu.demo.testproject.ui.mapper.CurrencyDomainToUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { provideCurrencyInfoFilter() }
    single { provideCurrencyDomainToUiMapper() }
    single { provideDispatcherProvider() }
    viewModel {
        DemoViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel { CurrencyListViewModel(get(), get()) }
}

fun provideCurrencyInfoFilter() = CurrencyInfoFilter()

fun provideCurrencyDomainToUiMapper() = CurrencyDomainToUiMapper()

fun provideDispatcherProvider() = androidDispatcherProvider