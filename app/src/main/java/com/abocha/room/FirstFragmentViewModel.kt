package com.abocha.room

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abocha.room.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FirstFragmentViewModel : ViewModel() {

    private val _flow: MutableStateFlow<List<NoteEntity?>> = MutableStateFlow(listOf())
    val flow: StateFlow<List<NoteEntity?>> = _flow.asStateFlow()

    fun requestData(context: Context) {
        viewModelScope.launch {
            (context as NotesApplication).dataBase.userDao().getAll().flowOn(Dispatchers.IO).collect {
                _flow.value = it
            }
        }
    }
}