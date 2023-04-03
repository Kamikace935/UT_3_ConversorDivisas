package com.example.ut_3_convensordivisas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class DivisAdapter(
    private val datos: List<String>
    ) :
    RecyclerView.Adapter<DivisAdapter.ViewHolder>() {

    private var sItPosition : Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.divisas_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = datos[position]

        if(sItPosition == position){
            holder.textView.setBackgroundColor(Color.parseColor("#FF0000"))
            holder.textView.setTextColor(Color.parseColor("white"))
        } else {
            holder.textView.setBackgroundColor(Color.parseColor("white"))
            holder.textView.setTextColor(Color.parseColor("black"))
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(sItPosition)
            sItPosition = holder.absoluteAdapterPosition
            notifyItemChanged(sItPosition)
        }

    }

    override fun getItemCount(): Int = datos.size

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.tvd)
    }

    fun getPosition(): Int {return sItPosition}

}