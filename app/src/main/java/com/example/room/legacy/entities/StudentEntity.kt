package com.example.room.legacy.entities

import android.content.ContentValues
import com.example.room.common.model.Student
import java.util.*

class StudentEntity(
    private val id: Int? = null,
    private val name: String,
    private val birthDay: Date
) : Entity<Student> {

    override fun asContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, name)
        contentValues.put(KEY_BIRTHDAY, birthDay.time)
        return contentValues
    }

    override fun toObject(): Student {
        return Student(id, name, birthDay)
    }

    companion object {
        const val TABLE_NAME = "student"
        const val KEY_ID = "ID"
        const val KEY_NAME = "NAME"
        const val KEY_BIRTHDAY = "BIRTHDAY"

        const val CREATE_TABLE_SQL =
            "CREATE TABLE $TABLE_NAME($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT NOT NULL, $KEY_BIRTHDAY INTEGER NOT NULL)"
        const val DROP_TABLE_SQL = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}