package com.example.fitnesspoy.model

import androidx.room.TypeConverter
import java.util.Date

class DataConverter {

    @TypeConverter
    fun toDate(longDate: Long?) : Date?{
        return if (longDate != null) Date(longDate) else null
    }
    @TypeConverter
    fun fromDate(date : Date?) : Long?{
        return date?.time
    }

}