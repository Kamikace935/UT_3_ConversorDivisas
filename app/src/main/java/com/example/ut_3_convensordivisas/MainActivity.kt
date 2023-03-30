package com.example.ut_3_convensordivisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var euros: EditText
    private lateinit var tv1: TextView
    private lateinit var divisas: RecyclerView
    private lateinit var vip: SwitchCompat
    private lateinit var bt: Button
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}