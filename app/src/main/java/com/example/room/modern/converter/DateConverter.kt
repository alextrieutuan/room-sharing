package com.example.room.modern.converter

import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun longToDate(value: Long): Date {
        return Calendar.getInstance().apply { timeInMillis = value }.time
    }

    @TypeConverter
    @JvmStatic
    fun dateToLong(date: Date): Long {
        return date.time
    }
}