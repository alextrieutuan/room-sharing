package com.example.room.modern

import androidx.room.*
import com.example.room.modern.ModernDatabase.Companion.DB_VERSION
import com.example.room.modern.converters.DateConverter
import com.example.room.modern.daos.StudentDao
import com.example.room.modern.entities.StudentEntity

@Database(entities = [StudentEntity::class], version = DB_VERSION)
@TypeConverters(DateConverter::class)
abstract class ModernDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        const val DB_NAME = "ROOM_DB"
        const val DB_VERSION = 1
    }
}
