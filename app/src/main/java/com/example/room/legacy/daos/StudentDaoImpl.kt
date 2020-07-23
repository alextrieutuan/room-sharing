package com.example.room.legacy.daos

import android.database.sqlite.SQLiteOpenHelper
import com.example.room.legacy.entities.StudentEntity
import java.util.*
import kotlin.collections.ArrayList

class StudentDaoImpl(private val sqlLiteDb: SQLiteOpenHelper) : StudentDao {

    override fun addNewStudent(entity: StudentEntity) : Boolean {
        val db = sqlLiteDb.writableDatabase
        val result = db.insert(StudentEntity.TABLE_NAME, null, entity.asContentValues())
        db.close()
        return result != -1L
    }

    override fun deleteStudent(studentId: Int) : Boolean {
        val db = sqlLiteDb.writableDatabase
        val affectedRow = db.delete(StudentEntity.TABLE_NAME, StudentEntity.KEY_ID + " = ?", arrayOf(studentId.toString()))
        db.close()
        return affectedRow != 0
    }

    override fun updateStudent(id: String, entity: StudentEntity) : Boolean {
        val db = sqlLiteDb.writableDatabase
        val affectedRow = db.update(StudentEntity.TABLE_NAME, entity.asContentValues(), StudentEntity.KEY_ID + " = ?", arrayOf(id))
        db.close()
        return affectedRow != 0
    }

    override fun getAllStudents(): List<StudentEntity> {
        val db = sqlLiteDb.readableDatabase

        val studentList = ArrayList<StudentEntity>()
        val query = "SELECT * FROM ${StudentEntity.TABLE_NAME}"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(StudentEntity.KEY_ID))
                val name = cursor.getString(cursor.getColumnIndex(StudentEntity.KEY_NAME))
                val birthDayTimeStamp = cursor.getLong(cursor.getColumnIndex(StudentEntity.KEY_BIRTHDAY))
                val birthDay = Calendar.getInstance().apply { timeInMillis = birthDayTimeStamp }.time
                studentList.add(StudentEntity(id, name, birthDay))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return studentList
    }

}