package com.example.room.modern

import androidx.room.*
import com.example.room.modern.converter.DateConverter
import com.example.room.modern.daos.StudentDao
import com.example.room.modern.entities.StudentEntity

@Database(entities = [StudentEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ModernDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {

        const val DB_NAME = "ROOM_DB"
    }
}
