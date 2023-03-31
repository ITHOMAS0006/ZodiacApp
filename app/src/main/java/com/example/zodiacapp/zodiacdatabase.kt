package com.example.zodiacapp

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [zodiacdb::class], version = 2,  autoMigrations = [
    AutoMigration (from = 1, to = 2)
],exportSchema = true)
abstract class zodiacdatabase: RoomDatabase() {

    abstract fun zodiacDao(): zodiacdao

    companion object {

        @Volatile
        private var INSTANCE: zodiacdatabase? = null

        fun getDatabase(context: Context): zodiacdatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    zodiacdatabase::class.java,
                    "zodiac_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}