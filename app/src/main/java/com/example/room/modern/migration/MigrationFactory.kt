package com.example.room.modern.migration

import androidx.room.migration.Migration

object MigrationFactory {

    private val MIGRATIONS = arrayOf<Migration>(
        Migration0102()
    )

    val migrations: Array<Migration>
        get() = MIGRATIONS.clone()
}