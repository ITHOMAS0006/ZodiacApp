package com.example.zodiacapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class zodiacdb(
    @PrimaryKey(autoGenerate = false)
    val name: String,
   @ColumnInfo(name="imageResourceId", defaultValue = "0")
    val imageResourceId: Int,
    val description: String,
    val zdsymbol: String,
    val month: String


)
