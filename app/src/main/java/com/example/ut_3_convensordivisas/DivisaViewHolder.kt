package com.example.ut_3_convensordivisas

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DivisaViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val divisa = view.findViewById<TextView>(R.id.tvd)

    fun render(divisaModel: String) {
        divisa.text = divisaModel
    }
}