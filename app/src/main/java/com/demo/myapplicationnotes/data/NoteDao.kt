package com.demo.myapplicationnotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM note where id=:noteId")
    suspend fun delete(noteId: Long)

    @Query("SELECT * FROM note where id=:noteId")
    suspend fun getNoteById(noteId: Long): Note
}