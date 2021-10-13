package com.example.xmlformat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_item.view.*

class RecyclerAdapter (val studentsList: ArrayList<studentData>): RecyclerView.Adapter<RecyclerAdapter.itemViewHolder>() {
    class itemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.student_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val stuName=studentsList[position].name
        val stuMark=studentsList[position].mark

        holder.itemView.apply {
            tvName.text=stuName
            tvMark.text=stuMark.toString()
        }
    }

    override fun getItemCount(): Int = studentsList.size
}