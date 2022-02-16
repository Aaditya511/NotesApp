package com.demo.myapplicationnotes

import android.app.Application
import com.demo.myapplicationnotes.data.NoteRoomDatabase
import com.demo.myapplicationnotes.repo.NotesRepo

class NotesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NoteRoomDatabase.getDatabase(this) }
    val repository by lazy { NotesRepo(database.noteDao()) }
}