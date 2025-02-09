package com.hoahieu.demo.testproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hoahieu.demo.testproject.data.model.CryptoDataModel
import com.hoahieu.demo.testproject.data.model.FiatDataModel

@Database(
    entities = [
        CryptoDataModel::class,
        FiatDataModel::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
    abstract fun fiatDao(): FiatDao
}