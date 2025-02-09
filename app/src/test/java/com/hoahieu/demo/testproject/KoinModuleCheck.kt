package com.hoahieu.demo.testproject

import com.hoahieu.demo.testproject.di.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class KoinModuleCheck {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModules() {
        appModule.verify()
    }
}