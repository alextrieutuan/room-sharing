package com.example.room.legacy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.room.legacy.daos.StudentDao
import com.example.room.legacy.daos.StudentDaoImpl
import com.example.room.legacy.entities.StudentEntity
import com.example.room.legacy.migrations.MigrationFactory

class LegacyDatabase private constructor(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), DaoFactory {

    private var studentDao: StudentDao? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(StudentEntity.CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Run migration from oldVersion to newVersion
        MigrationFactory.getMigrationByVersion(oldVersion, newVersion)?.migrate(db)
    }

    override fun studentDao(): StudentDao {
        if (studentDao == null) {
            studentDao = StudentDaoImpl(this)
        }
        return studentDao!!
    }

    companion object {
        private const val DB_NAME = "SQLiteOpenHelperDB"
        private const val DB_VERSION = 1

        private var databaseInstance : LegacyDatabase? = null

        fun getInstance(context: Context): LegacyDatabase {
            synchronized(this) {
                if (databaseInstance == null) {
                    databaseInstance = LegacyDatabase(context)
                }
                return databaseInstance!!
            }
        }
    }
}