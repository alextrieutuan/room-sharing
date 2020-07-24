package com.example.room.modern.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.room.modern.ModernDatabase
import com.example.room.modern.entities.StudentEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*


@RunWith(RobolectricTestRunner::class)
class StudentDaoTest {

    private lateinit var sut : StudentDao
    private lateinit var database : ModernDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder<ModernDatabase>(context, ModernDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        sut = database.studentDao()
    }

    @Test
    fun addStudent() {
        val studentEntity = StudentEntity(name = "TuanDepTrai", birthDay = Calendar.getInstance().time)
        sut.addStudent(studentEntity).test().assertComplete()

        sut.getAllStudents().test()
            .assertValue { students ->
                students.size == 1 && students[0].name == "TuanDepTrai"
            }
    }

    @Test
    fun getAllStudents() {
        for (i in 1 until 10) {
            sut.addStudent(StudentEntity(name = "TuanDepZai$i", birthDay = Date())).test()
        }

        sut.getAllStudents().test()
            .assertValue { students ->
                students.size == 9 && students[0].name == "TuanDepZai1"
            }
    }

    @Test
    fun getStudentById() {
        val studentEntity = StudentEntity(id = 1, name = "TuanDepTrai", birthDay = Calendar.getInstance().time)
        sut.addStudent(studentEntity).test().assertComplete()

        sut.getStudentById(1).test().assertComplete().assertValue {
            it.name == "TuanDepTrai"
        }

        sut.getStudentById(2).test().assertNoValues()
    }

    @Test
    fun deleteStudentById() {
        val studentEntity1 = StudentEntity(id = 1, name = "TuanDepTrai", birthDay = Calendar.getInstance().time)
        sut.addStudent(studentEntity1).test().assertComplete()

        val studentEntity2 = StudentEntity(id = 2, name = "TuanDepTrai", birthDay = Calendar.getInstance().time)
        sut.addStudent(studentEntity2).test().assertComplete()

        sut.getAllStudents().test().assertValue {
            it.size == 2
        }

        sut.deleteStudent(1).test().assertComplete()

        sut.getAllStudents().test().assertValue {
            it.size == 1 && it[0].id == 2
        }
    }

    @Test
    fun updateStudent() {
        val studentEntity1 = StudentEntity(id = 1, name = "TuanDepTrai", birthDay = Calendar.getInstance().time)
        sut.addStudent(studentEntity1).test().assertComplete()

        val studentEntityUpdate = StudentEntity(1, "TuanDepTraiVai", Date())
        sut.updateStudent(studentEntityUpdate)

        sut.getStudentById(1).test().assertValue {
            it.name == "TuanDepTraiVai"
        }
    }

}