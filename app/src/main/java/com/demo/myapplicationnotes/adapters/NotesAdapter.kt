package com.demo.myapplicationnotes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.myapplicationnotes.R
import com.demo.myapplicationnotes.callbacks.NoteItemClickListener
import com.demo.myapplicationnotes.data.Note
import com.demo.myapplicationnotes.viewholders.ViewHolder

class NotesAdapter(private val listOfNote: List<Note>, private val noteItemCallback: NoteItemClickListener) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = listOfNote[position]
        holder.title.text = note.title
        holder.description.text = note.content
        holder.itemView.setOnClickListener {
            noteItemCallback.onNoteClick(note)
        }
        holder.deleteButton.setOnClickListener {
            noteItemCallback.onDeleteButtonClick(note)
        }
    }

    override fun getItemCount(): Int {
        return listOfNote.size
    }
}