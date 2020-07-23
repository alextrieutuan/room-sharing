package com.example.room.legacy.daos

import com.example.room.legacy.entities.StudentEntity

interface StudentDao {

    fun addNewStudent(entity: StudentEntity) : Boolean

    fun deleteStudent(studentId: Int) : Boolean

    fun getAllStudents(): List<StudentEntity>

    fun updateStudent(id: String, entity: StudentEntity): Boolean
}