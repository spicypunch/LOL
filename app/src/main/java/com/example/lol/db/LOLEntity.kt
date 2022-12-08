package com.example.lol.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LOLEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name = "champName") val champName: String,
    @ColumnInfo(name = "champLevel") val champLevel: Int,
    @ColumnInfo(name = "champPoints") val champPoints: Int,
        )