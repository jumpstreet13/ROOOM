package com.abocha.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<NoteEntity>>

    @Insert
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}