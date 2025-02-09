package com.hoahieu.demo.testproject

import com.hoahieu.demo.testproject.ui.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
val testDispatcherProvider = object : DispatcherProvider {
    override val io = UnconfinedTestDispatcher()
    override val main = UnconfinedTestDispatcher()
    override val unconfined = UnconfinedTestDispatcher()
    override val default = UnconfinedTestDispatcher()
}