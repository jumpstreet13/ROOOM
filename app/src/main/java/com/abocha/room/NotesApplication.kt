package com.abocha.room

import android.app.Application
import androidx.room.Room

class NotesApplication : Application() {

    lateinit var dataBase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "notesDatabase"
        ).build()
    }
    // обсудить migrations
}