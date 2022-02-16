package com.demo.myapplicationnotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.demo.myapplicationnotes.NotesApplication
import com.demo.myapplicationnotes.databinding.ActivityMainBinding
import com.demo.myapplicationnotes.viewmodel.NoteViewModelFactory
import com.demo.myapplicationnotes.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity() {
    val noteViewModel: NotesViewModel by viewModels(factoryProducer = {
        NoteViewModelFactory((application as NotesApplication).repository)
    })
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}