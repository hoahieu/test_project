package com.hoahieu.demo.testproject

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hoahieu.demo.testproject.di.appModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.koinApplication

@RunWith(AndroidJUnit4::class)
class KoinInstrumentedCheck {
    @Test
    fun checkAllModules() {
        koinApplication {
            modules(appModule)
        }
    }
}