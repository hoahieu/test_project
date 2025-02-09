package com.hoahieu.demo.testproject.data.database

import android.content.Context
import androidx.room.Room

private val DATABASE_NAME = "demo.db"
fun createRoomDatabase(applicationContext: Context) = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java,
    DATABASE_NAME
).build()