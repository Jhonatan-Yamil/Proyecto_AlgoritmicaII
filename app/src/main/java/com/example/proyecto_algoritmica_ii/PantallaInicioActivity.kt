package com.example.proyecto_algoritmica_ii

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PantallaInicioActivity : AppCompatActivity() {

    lateinit var button: Button
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_inicio)
        button = findViewById(R.id.boton_comenzar)

        button.setOnClickListener {
            val intent: Intent = Intent(context, ExplicacionActivity::class.java)
            startActivity(intent)
        }
    }
}