package com.hoahieu.demo.testproject.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val default: CoroutineDispatcher
}

val androidDispatcherProvider: DispatcherProvider = object : DispatcherProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val unconfined = Dispatchers.Unconfined
    override val default = Dispatchers.Default
}