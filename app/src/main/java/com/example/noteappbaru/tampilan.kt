package com.example.noteappbaru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tampilan.*

class tampilan : AppCompatActivity() {
    var noteList : ArrayList<Note>? = null
    var notesList : ArrayList<Note>? = null
    var db : NoteDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan)
        noteList = ArrayList()
        notesList = ArrayList()
        val adapter = NoteListAdapter(this, notesList!!)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        noteList = db!!.readNotes()

        for (i:Note in noteList!!){
            val note = Note()
            note.id = i.id
            note.judul = i.judul
            note.deskripsi = i.deskripsi
            note.waktu = i.waktu
            noteList!!.add(note)
        }
        adapter.notifyDataSetChanged()
    }
}
