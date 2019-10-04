package com.example.noteappbaru

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_input_data.view.*
import kotlinx.android.synthetic.main.tv_item.view.*

class NoteListAdapter(val context: Context, val list: ArrayList<Note>): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: NoteListAdapter.ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tv_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        override fun onClick(v: View?) {
            val position = adapterPosition
            val note = list[position]
            when (v!!.id){
                itemView.delete_btn.id -> {
                    deleteNote(note.id!!)
                }
            }
        }
        fun deleteNote (id: Int){
            val db = NoteDatabaseHandler(context)
            db.deleteNote(id)
        }
        fun editNote (note: Note){
            val db = NoteDatabaseHandler(context)
            val view = LayoutInflater.from(context).inflate(R.layout.activity_input_data, null)
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context).setView(view)
            val dialog: AlertDialog = dialogBuilder!!.create()
            dialog?.show()
            view.tampilkan_data_btn.isEnabled = false
            view.simpan_tv.setOnClickListener {
                val judul = view.judul_list_tv.text.toString()
                val deskripsi = view.deskripsi_tv.text.toString()

                if(!TextUtils.isEmpty(judul) && !TextUtils.isEmpty(deskripsi)){
                    note.judul = judul
                    note.deskripsi = deskripsi

                    Toast.makeText(context, "Tersimpan", Toast.LENGTH_SHORT).show()
                    db.updateNote(note)
                    notifyItemChanged(adapterPosition, note)

                    dialog.dismiss()
                }
            }
        }

        fun bindItem(note:Note){
            itemView.judul_list_tv.text=note.judul
            itemView.deskripsi_list_tv.text=note.deskripsi
            itemView.waktu_list_tv.text=note.tanggal()

            itemView.delete_btn.setOnClickListener(this)
            itemView.edit_btn.setOnClickListener(this)
        }
    }
}