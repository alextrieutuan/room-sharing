package com.example.room.legacy.migrations

object MigrationFactory {

    private val MIGRATIONS = hashMapOf<String, Migration>(
        "1-2" to Migration0102()
    )

    fun getMigrationByVersion(startVersion: Int, endVersion: Int): Migration? {
        val key = "$startVersion-$endVersion"
        return MIGRATIONS[key]
    }

}