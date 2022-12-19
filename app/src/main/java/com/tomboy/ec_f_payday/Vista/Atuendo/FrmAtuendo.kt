package com.tomboy.ec_f_payday.Vista.Atuendo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.ModeloVista.VistaAtuendo
import com.tomboy.ec_f_payday.databinding.ActivityFrmAtuendoBinding

class FrmAtuendo : AppCompatActivity() {
    lateinit var b: ActivityFrmAtuendoBinding
    lateinit var vistaAtuendo: VistaAtuendo
    var id: Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmAtuendoBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if(op==1){
            b.btnGuardar.visibility = View.VISIBLE
            b.btnactualizar.visibility=View.INVISIBLE
            b.btneliminar.visibility=View.INVISIBLE
        }
        if(op==2){
            id = datos.getLong("id").toLong()
            vistaAtuendo = ViewModelProvider(this).get()
            vistaAtuendo.buscarid(id,b)
        }

        b.btnactualizar.setOnClickListener {
            var a= Atuendo(id,b.txtNom.text.toString(),
                b.txtDesc.text.toString())
            vistaAtuendo=ViewModelProvider(this).get()
            vistaAtuendo.actualizar(a)
            val v=Intent(this, AtuendoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btneliminar.setOnClickListener {
            var a=Atuendo(id,b.txtNom.text.toString(),
                b.txtDesc.text.toString())
            vistaAtuendo=ViewModelProvider(this).get()
            vistaAtuendo.eliminar(a)
            val v=Intent(this, AtuendoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnGuardar.setOnClickListener {
            var a = Atuendo(0, b.txtDesc.text.toString(),
                b.txtNom.text.toString())

            vistaAtuendo = ViewModelProvider(this).get()
            vistaAtuendo.registrar(a)

            val v = Intent(this, AtuendoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }
}