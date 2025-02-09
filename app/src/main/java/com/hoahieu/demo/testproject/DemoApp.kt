package com.hoahieu.demo.testproject

import android.app.Application
import com.hoahieu.demo.testproject.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApp)
            modules(appModule)
        }
    }
}