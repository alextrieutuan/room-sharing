package com.example.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.room.di.ApplicationDI
import com.example.room.modern.entities.StudentEntity
import java.util.*

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    private fun exampleRoomDB() {
        val entity = StudentEntity(name = "TuanDepTrai", birthDay = Date())
        ApplicationDI.provideRoomDatabase().studentDao().addStudent(entity).subscribe()
    }

    private fun exampleLegacyDB() {
        val entity = com.example.room.legacy.entities.StudentEntity(name = "TuanDepTrai", birthDay = Date())
        ApplicationDI.provideSqliteDataBase().studentDao().addStudent(entity)
    }
}