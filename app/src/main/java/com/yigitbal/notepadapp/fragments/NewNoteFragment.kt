package com.yigitbal.notepadapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yigitbal.notepadapp.MainActivity
import com.yigitbal.notepadapp.R
import com.yigitbal.notepadapp.databinding.FragmentNewNoteBinding
import com.yigitbal.notepadapp.model.Note
import com.yigitbal.notepadapp.helper.toast
import com.yigitbal.notepadapp.viewmodel.NoteViewModel


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(
            inflater,
            container,
            false
        )

        binding.fabGoBack.setOnClickListener { mView ->
            mView.findNavController()
                .navigate(R.id.action_newNoteFragment_to_homeFragment)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.editNoteTitle.text.toString().trim()
        val noteBody = binding.editNoteBody.text.toString().trim()

        if (noteTitle.isEmpty()) {
            val note = Note(0, noteTitle, noteBody)
            noteViewModel.addNote(note)
            Snackbar.make(
                view,
                "Note saved successfully",
                Snackbar.LENGTH_SHORT
            )
                .show()

            view.findNavController().navigate(
                R.id.action_newNoteFragment_to_homeFragment
            )

        } else {
            activity?.toast("Please enter note title")
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_menu -> {
                saveNote(mView)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}