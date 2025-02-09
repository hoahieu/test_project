package com.hoahieu.demo.testproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hoahieu.demo.testproject.data.model.CryptoDataModel

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto")
    fun getAll(): List<CryptoDataModel>

    @Query("DELETE FROM crypto")
    fun deleteAll()

    @Insert
    fun insertAll(cryptos: List<CryptoDataModel>)
}