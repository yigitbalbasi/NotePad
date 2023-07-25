package com.yigitbal.notepadapp.repository

import com.yigitbal.notepadapp.db.NoteDatabase
import com.yigitbal.notepadapp.model.Note

class NoteRepository(private val db: NoteDatabase) {

    class NoteRepository(private val db: NoteDatabase) {
        suspend fun addNote(note: Note) = db.getNoteDao().insertNote(note)
        suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
        suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
        fun getAllNotes() = db.getNoteDao().getAllNotes()
        fun searchNote(query: String?) = db.getNoteDao().searchNote(query)


    }
}