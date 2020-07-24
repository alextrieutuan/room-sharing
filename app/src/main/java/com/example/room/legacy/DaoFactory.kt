package com.example.room.legacy

import com.example.room.legacy.daos.StudentDao

interface DaoFactory {

    fun studentDao() : StudentDao
}