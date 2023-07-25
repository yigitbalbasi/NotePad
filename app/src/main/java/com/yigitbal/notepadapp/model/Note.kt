package com.yigitbal.notepadapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var noteTitle: String,
    var noteBody: String
) : Parcelable
