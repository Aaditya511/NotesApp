package com.demo.myapplicationnotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.demo.myapplicationnotes.data.Note
import com.demo.myapplicationnotes.data.NoteRoomDatabase
import com.demo.myapplicationnotes.repo.NotesRepo
import kotlinx.coroutines.launch

class NotesViewModel constructor(val noteRepo: NotesRepo) : ViewModel() {
    val notesData = noteRepo.getNotes().asLiveData()

    fun saveNote(note: Note) {
        viewModelScope.launch {
            noteRepo.saveNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch { noteRepo.deleteNote(note) }
    }

    suspend fun getNoteById(noteId: Long): Note {
        return noteRepo.getNoteById(noteId)
    }

}