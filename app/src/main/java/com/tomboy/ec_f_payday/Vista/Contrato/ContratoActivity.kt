package com.tomboy.ec_f_payday.Vista.Contrato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomboy.ec_f_payday.Adaptador.AdaptadorListadoContrato
import com.tomboy.ec_f_payday.ModeloVista.VistaContrato
import com.tomboy.ec_f_payday.Vista.Atuendo.FrmAtuendo
import com.tomboy.ec_f_payday.databinding.ActivityContratoBinding

class ContratoActivity : AppCompatActivity() {
    lateinit var binding: ActivityContratoBinding
    lateinit var  vistaContrato: VistaContrato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContratoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        vistaContrato= ViewModelProvider(this).get()
        vistaContrato.iniciar()
        binding.listado.apply {
            layoutManager= LinearLayoutManager(applicationContext)
        }

        vistaContrato.lista.observe(this, Observer{
            binding.listado.adapter= AdaptadorListadoContrato(it)
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
                    vistaContrato.buscarnombre(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    vistaContrato.buscarnombre(p0.toString())
                }
            }
        })
    }
}