package com.example.noteappbaru

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        fun bindItem(note:Note){
            itemView.judul_list_tv.text=note.judul
            itemView.deskripsi_list_tv.text=note.deskripsi
            itemView.waktu_list_tv.text=note.tanggal()

            itemView.delete_btn.setOnClickListener(this)
            itemView.edit_btn.setOnClickListener(this)
        }
    }
}