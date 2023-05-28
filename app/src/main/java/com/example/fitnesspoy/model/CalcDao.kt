package com.example.fitnesspoy.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CalcDao {
    @Insert
     fun insert(calc : Calc)
     /*Query = enviar
     * Delete
     * UpDate
     * Insert = enserir*/
}