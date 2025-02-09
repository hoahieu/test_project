package com.hoahieu.demo.testproject.di

import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule,
        networkModule
    )
}