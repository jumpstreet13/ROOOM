package com.abocha.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abocha.room.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel : ViewModel() {

    fun addNewNote(noteText: String, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                (context as NotesApplication).dataBase.userDao().insert(
                    NoteEntity(noteText = noteText, isLiked = false)
                )
            }
        }
    }
}