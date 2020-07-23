package com.example.room.legacy.migrations

import android.database.sqlite.SQLiteDatabase

abstract class Migration (private val fromVersion: Int, private val toVersion: Int) {

    abstract fun migrate(sqlSQLiteDatabase: SQLiteDatabase)
}