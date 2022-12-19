package com.tomboy.ec_f_payday.Vista.Miembro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomboy.ec_f_payday.Adaptador.AdaptadorListadoMiembro
import com.tomboy.ec_f_payday.ModeloVista.VistaMiembro
import com.tomboy.ec_f_payday.Vista.Atuendo.FrmAtuendo
import com.tomboy.ec_f_payday.databinding.ActivityMiembroBinding

class MiembroActivity : AppCompatActivity() {
    lateinit var binding: ActivityMiembroBinding
    lateinit var vistaMiembro: VistaMiembro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMiembroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        vistaMiembro= ViewModelProvider(this).get()
        vistaMiembro.iniciar()
        binding.listado.apply {
            layoutManager= LinearLayoutManager(applicationContext)
        }

        vistaMiembro.lista.observe(this, Observer{
            binding.listado.adapter= AdaptadorListadoMiembro(it)
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
                    vistaMiembro.buscarnombre(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    vistaMiembro.buscarnombre(p0.toString())
                }
            }
        })
    }
}