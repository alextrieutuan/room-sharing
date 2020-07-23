package com.example.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.room.legacy.LegacyDatabase
import com.example.room.legacy.entities.StudentEntity
import com.example.room.modern.ModernDatabase
import com.example.room.modern.migration.MigrationFactory
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ExampleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val studentEntity = StudentEntity(name = "Tuan", birthDay = Date())
        LegacyDatabase.getDatabase(this).provideStudentDao().addNewStudent(studentEntity)

        val modernDb =  Room.databaseBuilder(application, ModernDatabase::class.java, ModernDatabase.DB_NAME)
            .addMigrations(*MigrationFactory.migrations)
            .fallbackToDestructiveMigration()
            .build()

        val newStudentEntity = com.example.room.modern.entities.StudentEntity(name = "Tuan", birthDay = Date())
        modernDb.studentDao().addStudent(newStudentEntity).subscribe(
            {
                // Inserted
            },
            {

            }
        ).let { compositeDisposable.add(it) }
    }

}