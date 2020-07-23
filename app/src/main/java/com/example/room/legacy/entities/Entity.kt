package com.example.room.legacy.entities

import android.content.ContentValues

interface Entity<T> {

    fun asContentValues(): ContentValues

    fun toObject() : T
}