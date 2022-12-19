package com.tomboy.ec_f_payday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tomboy.ec_f_payday.Vista.Atuendo.AtuendoActivity
import com.tomboy.ec_f_payday.Vista.Comprador.CompradorActivity
import com.tomboy.ec_f_payday.Vista.Contrato.ContratoActivity
import com.tomboy.ec_f_payday.Vista.Equipo.EquipoActivity
import com.tomboy.ec_f_payday.Vista.Imagen.ImagenActivity
import com.tomboy.ec_f_payday.Vista.Miembro.MiembroActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAtuendo: Button = findViewById(R.id.btn_atuendo)
        val btnComprador: Button = findViewById(R.id.btn_comprador)
        val btnContrato: Button = findViewById(R.id.btn_contrato)
        val btnEquipo: Button = findViewById(R.id.btn_equipo)
        val btnImagen: Button = findViewById(R.id.btn_imagen)
        val btnMiembro: Button = findViewById(R.id.btn_miembro)

        btnAtuendo.setOnClickListener {
            val intent: Intent = Intent(this, AtuendoActivity:: class.java)
            startActivity((intent))
        }
        btnComprador.setOnClickListener {
            val intent: Intent = Intent(this, CompradorActivity:: class.java)
            startActivity((intent))
        }
        btnContrato.setOnClickListener {
            val intent: Intent = Intent(this, ContratoActivity:: class.java)
            startActivity((intent))
        }
        btnEquipo.setOnClickListener {
            val intent: Intent = Intent(this, EquipoActivity:: class.java)
            startActivity((intent))
        }
        btnImagen.setOnClickListener {
            val intent: Intent = Intent(this, ImagenActivity:: class.java)
            startActivity((intent))
        }
        btnMiembro.setOnClickListener {
            val intent: Intent = Intent(this, MiembroActivity:: class.java)
            startActivity((intent))
        }

    }
}