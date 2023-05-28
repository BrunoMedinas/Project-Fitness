package com.example.fitnesspoy.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Calc ::class], version = 1)
@TypeConverters(DataConverter::class)

abstract class AppDateBase : RoomDatabase(){
    abstract fun calcDao() : CalcDao

    companion object{
        private var INSTACE : AppDateBase? = null

        fun getDataBase(context: Context) : AppDateBase {
            if (INSTACE == null) {
                synchronized(this) {
                    INSTACE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDateBase::class.java,
                        "fitness_tracker"
                    ).build()
                }
                return INSTACE as AppDateBase
            } else {
                return INSTACE as AppDateBase
            }

        }
    }
}