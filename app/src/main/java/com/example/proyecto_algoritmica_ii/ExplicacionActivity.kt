package com.example.proyecto_algoritmica_ii

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ExplicacionActivity : AppCompatActivity() {

    lateinit var button: Button
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacion)
        button = findViewById(R.id.boton_entendido)

        button.setOnClickListener {
            val intent: Intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}