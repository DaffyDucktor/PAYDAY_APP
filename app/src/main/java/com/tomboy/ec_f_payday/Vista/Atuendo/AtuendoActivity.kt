package com.tomboy.ec_f_payday.Vista.Atuendo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomboy.ec_f_payday.Adaptador.AdaptadorListadoAtuendo
import com.tomboy.ec_f_payday.ModeloVista.VistaAtuendo
import com.tomboy.ec_f_payday.databinding.ActivityAtuendoBinding

class AtuendoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAtuendoBinding
    lateinit var  vistaAtuendo: VistaAtuendo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAtuendoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        vistaAtuendo= ViewModelProvider(this).get()
        vistaAtuendo.iniciar()
        binding.listado.apply {
            layoutManager= LinearLayoutManager(applicationContext)
        }

        vistaAtuendo.lista.observe(this, Observer{
            binding.listado.adapter= AdaptadorListadoAtuendo(it)
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
                    vistaAtuendo.buscarnombre(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    vistaAtuendo.buscarnombre(p0.toString())
                }
            }
        })
    }
}