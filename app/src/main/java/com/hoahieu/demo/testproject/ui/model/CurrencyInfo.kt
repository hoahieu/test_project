package com.hoahieu.demo.testproject.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyInfo(
    val id: String,
    val name: String,
    val code: String
) : Parcelable