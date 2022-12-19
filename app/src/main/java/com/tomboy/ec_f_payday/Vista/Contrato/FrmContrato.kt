package com.tomboy.ec_f_payday.Vista.Contrato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Comprador
import com.tomboy.ec_f_payday.Modelo.Contrato
import com.tomboy.ec_f_payday.ModeloVista.VistaContrato
import com.tomboy.ec_f_payday.Vista.Comprador.CompradorActivity
import com.tomboy.ec_f_payday.databinding.ActivityFrmContratoBinding

class FrmContrato : AppCompatActivity() {
    lateinit var b: ActivityFrmContratoBinding
    lateinit var vistaContrato: VistaContrato
    var id: Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmContratoBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if(op==1){
            b.btnGuardar.visibility = View.VISIBLE
        }
        if(op==2){
            id = datos.getLong("id").toLong()
            vistaContrato = ViewModelProvider(this).get()
            vistaContrato.buscarid(id,b)
        }

        b.btnGuardar.setOnClickListener {
            var c = Contrato(0, b.txtFec.text.toString(),
                b.txtNom.text.toString(), b.txtPre.text.toString(),
                0, 0, 0, 0)

            vistaContrato = ViewModelProvider(this).get()
            vistaContrato.registrar(c)

            val v = Intent(this, MainActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnactualizar.setOnClickListener {
            var c = Contrato(id,b.txtNom.text.toString(),
                b.txtPre.text.toString(), b.txtFec.text.toString(),
                1, 1, 1, 1)
            vistaContrato=ViewModelProvider(this).get()
            vistaContrato.actualizar(c)
            val v=Intent(this, ContratoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btneliminar.setOnClickListener {
            var c = Contrato(id,b.txtNom.text.toString(),
                b.txtPre.text.toString(), b.txtFec.text.toString(),
                1, 1, 1, 1)
            vistaContrato=ViewModelProvider(this).get()
            vistaContrato.eliminar(c)
            val v=Intent(this, ContratoActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }
}