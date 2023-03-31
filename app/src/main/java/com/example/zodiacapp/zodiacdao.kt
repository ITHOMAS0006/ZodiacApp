package com.example.zodiacapp

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface zodiacdao {

    @Query("SELECT * FROM zodiacdb")
    fun getAllZodiacs(): List<zodiacdb>
    @Query("SELECT * FROM zodiacdb WHERE name=:zodiacelement")
   suspend fun getZodiacByName(zodiacelement: String): zodiacdb

    @Insert
    fun insertZodiacs(zodiacelement: zodiacdb)
}