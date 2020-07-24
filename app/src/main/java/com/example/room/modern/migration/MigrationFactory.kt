package com.example.room.modern.migration

import androidx.room.migration.Migration

object MigrationFactory {

    val migrations = arrayOf<Migration>(
        Migration0102()
    )
}