package com.example.noteappbaru

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

const val DATABASE_VERSION =1
const val DATABASE_NAME="note.db"
const val TABLE_NAME="note"

const val KEY_ID ="id"
const val KEY_JUDUL="judul"
const val KEY_DESKRIPSI = "deskripsi"
const val KEY_WAKTU="waktu"

class NoteDatabaseHandler (val context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createNoteTable = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY,"+
                "$KEY_JUDUL TEXT, $KEY_DESKRIPSI TEXT, $KEY_WAKTU LONG)"
        db?.execSQL(createNoteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun createNote(note: Note){
        val db = writableDatabase

        val values=ContentValues()
        values.put(KEY_JUDUL, note.judul)
        values.put(KEY_DESKRIPSI, note.deskripsi)
        values.put(KEY_WAKTU, System.currentTimeMillis())

        db.insert(TABLE_NAME, null,values)
        Log.d("DATA INSERTED", "SUCCESS")
        db.close()
    }
    fun readNotes():ArrayList<Note>{
        val db = readableDatabase
        val list = ArrayList<Note>()

        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAll,null)

        if (cursor.moveToFirst()){
            do{
                val note = Note()
                note.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                note.judul=cursor.getString(cursor.getColumnIndex(KEY_JUDUL))
                note.deskripsi=cursor.getString(cursor.getColumnIndex(KEY_DESKRIPSI))
                note.waktu=cursor.getLong(cursor.getColumnIndex(KEY_WAKTU))
                list.add(note)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
    fun updateNote(note: Note):Int{
        val db= writableDatabase

        val values = ContentValues()
        values.put(KEY_JUDUL, note.judul)
        values.put(KEY_DESKRIPSI, note.deskripsi)
        values.put(KEY_WAKTU, System.currentTimeMillis())

        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(note.id.toString()))
    }
    fun deleteNote(id:Int){
        val db = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
    }
}