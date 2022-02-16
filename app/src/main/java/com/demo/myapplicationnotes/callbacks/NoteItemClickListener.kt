package com.demo.myapplicationnotes.callbacks

import com.demo.myapplicationnotes.data.Note

interface NoteItemClickListener {
    fun onDeleteButtonClick(note: Note)
    fun onNoteClick(note: Note)
}