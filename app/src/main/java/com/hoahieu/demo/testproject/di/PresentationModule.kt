package com.hoahieu.demo.testproject.di

import com.hoahieu.demo.testproject.ui.CurrencyInfoFilter
import com.hoahieu.demo.testproject.ui.androidDispatcherProvider
import com.hoahieu.demo.testproject.ui.base.DemoViewModel
import com.hoahieu.demo.testproject.ui.currencylist.CurrencyListViewModel
import com.hoahieu.demo.testproject.ui.mapper.CurrencyDomainToUiMapper
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule =
    module {
        singleOf(::CurrencyInfoFilter)
        singleOf(::CurrencyDomainToUiMapper)
        singleOf(::androidDispatcherProvider)
        viewModelOf(::DemoViewModel)
        viewModelOf(::CurrencyListViewModel)
    }
