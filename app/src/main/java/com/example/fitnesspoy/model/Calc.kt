package com.example.fitnesspoy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Calc(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "type") val type : String,
    @ColumnInfo(name = "res") val res : Double,
    @ColumnInfo(name = "create_date") val createDate: Date = Date()
)
