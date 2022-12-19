package com.tomboy.ec_f_payday.Vista.Equipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Contrato
import com.tomboy.ec_f_payday.Modelo.Equipo
import com.tomboy.ec_f_payday.ModeloVista.VistaEquipo
import com.tomboy.ec_f_payday.Vista.Contrato.ContratoActivity
import com.tomboy.ec_f_payday.databinding.ActivityFrmEquipoBinding

class FrmEquipo : AppCompatActivity() {
    lateinit var b: ActivityFrmEquipoBinding
    lateinit var vistaEquipo: VistaEquipo
    var id: Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmEquipoBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if(op==1){
            b.btnGuardar.visibility = View.VISIBLE
        }
        if(op==2){
            id = datos.getLong("id").toLong()
            vistaEquipo = ViewModelProvider(this).get()
            vistaEquipo.buscarid(id,b)
        }

        b.btnGuardar.setOnClickListener {
            var e = Equipo(0, b.txtDesc.text.toString(),
                b.txtNom.text.toString())

            vistaEquipo = ViewModelProvider(this).get()
            vistaEquipo.registrar(e)

            val v = Intent(this, MainActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnactualizar.setOnClickListener {
            var e = Equipo(id,b.txtNom.text.toString(),
                b.txtDesc.text.toString())
            vistaEquipo=ViewModelProvider(this).get()
            vistaEquipo.actualizar(e)
            val v=Intent(this, EquipoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btneliminar.setOnClickListener {
            var e = Equipo(id,b.txtNom.text.toString(),
                b.txtDesc.text.toString())
            vistaEquipo=ViewModelProvider(this).get()
            vistaEquipo.eliminar(e)
            val v=Intent(this, EquipoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }
}