package com.example.room.modern

import com.example.room.modern.daos.StudentDao

interface DaoFactory {

    fun provideStudentDao(): StudentDao
}