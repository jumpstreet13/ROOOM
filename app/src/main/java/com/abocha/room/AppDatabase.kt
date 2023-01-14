package com.abocha.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abocha.room.data.NoteDao
import com.abocha.room.data.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
}