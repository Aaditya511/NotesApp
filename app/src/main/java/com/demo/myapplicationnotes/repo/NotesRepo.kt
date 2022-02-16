package com.demo.myapplicationnotes.repo

import com.demo.myapplicationnotes.data.Note
import com.demo.myapplicationnotes.data.NoteDao
import kotlinx.coroutines.flow.Flow

class NotesRepo(private val noteDao: NoteDao) {

    suspend fun saveNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note.id)
    }

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun getNoteById(noteId: Long): Note {
        return noteDao.getNoteById(noteId)
    }
}