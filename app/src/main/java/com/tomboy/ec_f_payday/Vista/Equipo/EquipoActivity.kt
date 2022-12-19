package com.tomboy.ec_f_payday.Vista.Equipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomboy.ec_f_payday.Adaptador.AdaptadorListadoEquipo
import com.tomboy.ec_f_payday.ModeloVista.VistaEquipo
import com.tomboy.ec_f_payday.Vista.Atuendo.FrmAtuendo
import com.tomboy.ec_f_payday.databinding.ActivityEquipoBinding

class EquipoActivity : AppCompatActivity() {
    lateinit var binding: ActivityEquipoBinding
    lateinit var  vistaEquipo: VistaEquipo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEquipoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        vistaEquipo= ViewModelProvider(this).get()
        vistaEquipo.iniciar()
        binding.listado.apply {
            layoutManager= LinearLayoutManager(applicationContext)
        }

        vistaEquipo.lista.observe(this, Observer{
            binding.listado.adapter= AdaptadorListadoEquipo(it)
        })

        binding.btnagregar.setOnClickListener {
            val v = Intent(this, FrmAtuendo::class.java)
            v.putExtra("op", 1)
            startActivity(v)
        }

        binding.txtdato.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().isEmpty()){
                    vistaEquipo.buscarnombre(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    vistaEquipo.buscarnombre(p0.toString())
                }
            }
        })
    }
}