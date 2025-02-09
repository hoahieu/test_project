package com.hoahieu.demo.testproject.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.launch

fun Fragment.safeLaunch(action: suspend () -> Unit) {
    viewLifecycleOwner.lifecycle.coroutineScope.launch { action.invoke() }
}