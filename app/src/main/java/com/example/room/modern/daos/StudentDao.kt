package com.example.room.modern.daos

import androidx.room.*
import com.example.room.modern.entities.StudentEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudent(vararg studentEntities: StudentEntity) : Completable

    @Query("SELECT * FROM student")
    fun getAllStudents() : Single<List<StudentEntity>>

    @Query("DELETE FROM student WHERE student.id = :studentId")
    fun deleteStudent(studentId: Int) : Completable

    @Query("SELECT * FROM student WHERE student.id = :studentId LIMIT 1")
    fun getStudentById(studentId: Int) : Maybe<StudentEntity>

    @Update
    fun updateStudent(studentEntity: StudentEntity)
}