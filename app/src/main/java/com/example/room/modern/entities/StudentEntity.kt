package com.example.room.modern.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "student")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val name: String,

    val birthDay: Date
)