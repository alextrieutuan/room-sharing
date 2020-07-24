package com.example.room.legacy.daos

import androidx.annotation.Nullable
import com.example.room.legacy.entities.StudentEntity

interface StudentDao {

    fun addStudent(entity: StudentEntity) : Boolean

    fun deleteStudent(studentId: Int) : Boolean

    fun getAllStudents(): List<StudentEntity>

    fun updateStudent(id: String, entity: StudentEntity): Boolean

    fun getStudent(studentId: Int): StudentEntity?
}