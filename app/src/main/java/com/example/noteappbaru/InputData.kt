package com.example.noteappbaru

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input_data.*
import org.w3c.dom.Text

class InputData : AppCompatActivity() {
    var dbHandler: NoteDatabaseHandler? = null
    var progressDialog: ProgressDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)

        dbHandler = NoteDatabaseHandler(this)
        progressDialog = ProgressDialog(this)

        simpan_tv.setOnClickListener {
            progressDialog!!.setMessage("Saving...")
            progressDialog!!.show()

            if(!TextUtils.isEmpty(judul_tv.text) && !TextUtils.isEmpty(deskripsi_tv.text)){
                val note = Note()
                note.judul = judul_tv.text.toString()
                note.deskripsi = deskripsi_tv.text.toString()
                simpanKeDB(note)

                Toast.makeText(this, "Inserted Success", Toast.LENGTH_SHORT).show()
            }else if(!TextUtils.isEmpty(judul_tv.text) && !TextUtils.isEmpty(deskripsi_tv.text)){
                val note = Note()
                note.judul = judul_tv.text.toString()
                note.deskripsi = deskripsi_tv.text.toString()
                simpanKeDB(note)
            }else {
                Toast.makeText(this,"Tolong Masukkan Judul dan Deskripsi", Toast.LENGTH_SHORT).show()
            }
            progressDialog!!.dismiss()
        }
        tampilkan_data_btn.setOnClickListener {
            val intent = Intent(this, tampilan::class.java)
            startActivity(intent)
        }
    }
    fun simpanKeDB(note: Note){
        dbHandler!!.createNote(note)
    }
}
