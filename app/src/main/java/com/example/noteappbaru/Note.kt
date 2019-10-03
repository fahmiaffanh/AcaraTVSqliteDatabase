package com.example.noteappbaru

import android.icu.text.DateFormat
import java.sql.Date

class Note {
    var id:Int? = null
    var judul: String?=null
    var deskripsi: String?=null
    var waktu:Long?=null

    fun tanggal():String{
        val dateFormat = DateFormat.getDateInstance()
        val formattedDate = dateFormat.format(Date(waktu!!).time)
        return "Dibuat : $formattedDate"
    }
}