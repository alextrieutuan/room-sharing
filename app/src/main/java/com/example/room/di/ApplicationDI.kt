package com.example.room.di

import android.content.Context
import androidx.room.Room
import com.example.room.legacy.LegacyDatabase
import com.example.room.modern.ModernDatabase
import com.example.room.modern.migration.MigrationFactory

object ApplicationDI {

    private lateinit var context: Context

    private var isInitialized = false

    private var roomDatabase: ModernDatabase? = null
    private var sqliteDatabase: LegacyDatabase? = null

    fun initWith(context: Context) {
        if (isInitialized) {
            return
        }
        this.context = context
        isInitialized = true
    }

    fun provideRoomDatabase(): ModernDatabase {
        require(isInitialized)
        synchronized(this) {
            if (roomDatabase == null) {
                roomDatabase = Room.databaseBuilder(context.applicationContext, ModernDatabase::class.java, ModernDatabase.DB_NAME)
                    .addMigrations(*MigrationFactory.migrations)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return roomDatabase!!
        }
    }

    fun provideSqliteDataBase(): LegacyDatabase {
        require(isInitialized)
        synchronized(this) {
            if (sqliteDatabase == null) {
                sqliteDatabase = LegacyDatabase.getInstance(context.applicationContext)
            }
            return sqliteDatabase!!
        }
    }

}