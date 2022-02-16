package com.demo.myapplicationnotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.demo.myapplicationnotes.NotesApplication
import com.demo.myapplicationnotes.R
import com.demo.myapplicationnotes.data.Note
import com.demo.myapplicationnotes.databinding.FragmentAddOrEditBinding
import com.demo.myapplicationnotes.databinding.FragmentNotesBinding
import com.demo.myapplicationnotes.viewmodel.NoteViewModelFactory
import com.demo.myapplicationnotes.viewmodel.NotesViewModel
import kotlinx.coroutines.launch


class FragmentAddOrEdit : Fragment() {

    private lateinit var binding: FragmentAddOrEditBinding
    private val args: FragmentAddOrEditArgs by navArgs()
    private val noteViewModel: NotesViewModel by activityViewModels(factoryProducer = {
        NoteViewModelFactory((requireActivity().application as NotesApplication).repository)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddOrEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idBtn.setOnClickListener {
            saveButtonClick()
        }

        if (args.noteId != -1L) {
            //old Note
            setNoteData(args.noteId)
        }
    }

    fun setNoteData(noteId: Long) {
        lifecycleScope.launch {
            val note = noteViewModel.getNoteById(noteId)
            //set data on view
            binding.editNoteTitle.setText(note.title)
            binding.editNoteDesc.setText(note.content)
        }
    }

    fun saveButtonClick() {
        val title = binding.editNoteTitle.text.toString()
        val content = binding.editNoteDesc.text.toString()
        if (title.isNotBlank() && content.isNotBlank()) {

            val note = if (args.noteId == -1L) {
                Note(title, content)
            } else {
                Note(title, content, args.noteId)
            }
            noteViewModel.saveNote(note)
            Toast.makeText(context, "Note saved successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_SHORT).show()
        }
    }
}