package com.abocha.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note",
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "text") val noteText: String,
    @ColumnInfo(name = "isLiked") val isLiked: Boolean,
)