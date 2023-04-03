package com.example.ut_3_convensordivisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var euros: EditText
    private lateinit var tv1: TextView
    private lateinit var divisas: RecyclerView
    private lateinit var vip: SwitchCompat
    private lateinit var bt: Button
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var divAdapter: DivisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        euros = findViewById(R.id.euros)
        euros.addDecimalLimiter()
        tv1 = findViewById(R.id.tv1)

        divisas = findViewById(R.id.divisas)
        divisas.layoutManager = LinearLayoutManager(this)
        divAdapter = DivisAdapter(resources.getStringArray(R.array.divisas).toList())
        divisas.adapter = divAdapter

        vip = findViewById(R.id.vip)
        bt = findViewById(R.id.bt)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)


        bt.setOnClickListener {
            if (euros.text.isEmpty() || 0.00 == euros.text.toString().toDouble() ) {
                mensajeError("Debe introducir una cantidad")
            }else if(divAdapter.getPosition() == -1){
                mensajeError("Tiene que seleccionar una divisa")
            } else  {
                tv3.text = resultado()
            }

        }
    }

    private fun resultado(): String {
        val moneda = euros.text.toString().toDouble()
        var cambio = moneda * cambioMoneda(divAdapter.getPosition())

        if(!vip.isChecked) {
            cambio -= 0.01 * cambio
        }

        return String.format("%.2f",cambio)
    }
    private fun cambioMoneda(moneda: Int): Double {
        var cambio = 0.0
        when(moneda) {
            0 -> {cambio = 1.08}
            1 -> {cambio = 0.88}
            2 -> {cambio = 1.46}
            3 -> {cambio = 1.62}
            4 -> {cambio = 143.78}
            5 -> {cambio = 88.82}
            6 -> {cambio = 1.74}
            7 -> {cambio = 0.99}
            8 -> {cambio =  19.28}
            9 -> {cambio = 84.31}
        }

        return cambio
    }

    private fun mensajeError(error: String) {
        Toast.makeText(applicationContext,error, Toast.LENGTH_LONG).show()
    }

    fun EditText.addDecimalLimiter(maxLimit: Int = 2) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val str = this@addDecimalLimiter.text!!.toString()
                if (str.isEmpty()) {return}
                val str2 = decimalLimiter(str, maxLimit)
                if (str2 != str) {
                    this@addDecimalLimiter.setText(str2)
                    val pos = this@addDecimalLimiter.text!!.length
                    this@addDecimalLimiter.setSelection(pos)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
    fun EditText.decimalLimiter(string: String, MAX_DECIMAL: Int): String {
        var str = string
        if (str[0] == '.') str = "0$str"
        val max = str.length
        var rFinal = ""
        var after = false
        var i = 0
        var up = 0
        var decimal = 0
        var t: Char
        val decimalCount = str.count{ ".".contains(it) }

        if (decimalCount > 1)
            return str.dropLast(1)
        while (i < max) {
            t = str[i]
            if (t != '.' && !after) {
                up++
            } else if (t == '.') {
                after = true
            } else {
                decimal++
                if (decimal > MAX_DECIMAL)
                    return rFinal
            }
            rFinal += t
            i++
        }
        return rFinal
    }
}