package com.tomboy.ec_f_payday.Vista.Comprador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.Modelo.Comprador
import com.tomboy.ec_f_payday.ModeloVista.VistaComprador
import com.tomboy.ec_f_payday.Vista.Atuendo.AtuendoActivity
import com.tomboy.ec_f_payday.databinding.ActivityFrmCompradorBinding

class FrmComprador : AppCompatActivity() {
    lateinit var b: ActivityFrmCompradorBinding
    lateinit var vistaComprador: VistaComprador
    var id: Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmCompradorBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if(op==1){
            b.btnGuardar.visibility = View.VISIBLE
        }
        if(op==2){
            id = datos.getLong("id").toLong()
            vistaComprador = ViewModelProvider(this).get()
            vistaComprador.buscarid(id,b)
        }

        b.btnGuardar.setOnClickListener {
            var c = Comprador(0, b.txtPro.text.toString(),
                b.txtNom.text.toString())

            vistaComprador = ViewModelProvider(this).get()
            vistaComprador.registrar(c)

            val v = Intent(this, MainActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnActualizar.setOnClickListener {
            var c = Comprador(id,b.txtNom.text.toString(),
                b.txtPro.text.toString())
            vistaComprador=ViewModelProvider(this).get()
            vistaComprador.actualizar(c)
            val v=Intent(this, CompradorActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnEliminar.setOnClickListener {
            var c = Comprador(id,b.txtNom.text.toString(),
                b.txtPro.text.toString())
            vistaComprador=ViewModelProvider(this).get()
            vistaComprador.eliminar(c)
            val v=Intent(this, CompradorActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }

}