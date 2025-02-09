package com.hoahieu.demo.testproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hoahieu.demo.testproject.data.model.FiatDataModel

@Dao
interface FiatDao {
    @Query("SELECT * FROM fiat")
    fun getAll(): List<FiatDataModel>

    @Query("DELETE FROM fiat")
    fun deleteAll()

    @Insert
    fun insertAll(fiats: List<FiatDataModel>)
}