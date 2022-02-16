package com.demo.myapplicationnotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.myapplicationnotes.NotesApplication
import com.demo.myapplicationnotes.adapters.NotesAdapter
import com.demo.myapplicationnotes.callbacks.NoteItemClickListener
import com.demo.myapplicationnotes.data.Note
import com.demo.myapplicationnotes.databinding.FragmentNotesBinding
import com.demo.myapplicationnotes.viewmodel.NoteViewModelFactory
import com.demo.myapplicationnotes.viewmodel.NotesViewModel


class NotesFragment : Fragment(), NoteItemClickListener {

    private lateinit var binding: FragmentNotesBinding
    private val noteViewModel: NotesViewModel by activityViewModels(factoryProducer = {
        NoteViewModelFactory((requireActivity().application as NotesApplication).repository)
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                NotesFragmentDirections.actionNotesFragmentToFragmentAddOrEdit()
            )
        }

        noteViewModel.notesData.observe(viewLifecycleOwner, Observer {
            binding.recylerView.adapter = NotesAdapter(it, this)

        })
    }



    override fun onDeleteButtonClick(note: Note) {
        noteViewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Note) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToFragmentAddOrEdit(
                note.id
            )
        )
    }

}