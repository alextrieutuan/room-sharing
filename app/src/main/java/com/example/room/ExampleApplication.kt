package com.example.room

import android.app.Application
import com.example.room.di.ApplicationDI

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationDI.initWith(this)
    }
}