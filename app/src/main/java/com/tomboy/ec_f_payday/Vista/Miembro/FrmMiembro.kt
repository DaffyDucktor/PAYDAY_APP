package com.tomboy.ec_f_payday.Vista.Miembro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Imagen
import com.tomboy.ec_f_payday.Modelo.Miembro
import com.tomboy.ec_f_payday.ModeloVista.VistaMiembro
import com.tomboy.ec_f_payday.Vista.Imagen.ImagenActivity
import com.tomboy.ec_f_payday.databinding.ActivityFrmMiembroBinding

class FrmMiembro : AppCompatActivity() {
    lateinit var b: ActivityFrmMiembroBinding
    lateinit var vistaMiembro: VistaMiembro
    var id: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmMiembroBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if (op == 1) {
            b.btnGuardar.visibility = View.VISIBLE
        }
        if (op == 2) {
            id = datos.getLong("id").toLong()
            vistaMiembro = ViewModelProvider(this).get()
            vistaMiembro.buscarid(id, b)
        }

        b.btnGuardar.setOnClickListener {
            var m = Miembro(
                0, b.txtCar.text.toString(),
                b.txtNom.text.toString()
            )

            vistaMiembro = ViewModelProvider(this).get()
            vistaMiembro.registrar(m)

            val v = Intent(this, MainActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnactualizar.setOnClickListener {
            var m = Miembro(id,b.txtNom.text.toString(),
                b.txtCar.text.toString())
            vistaMiembro=ViewModelProvider(this).get()
            vistaMiembro.actualizar(m)
            val v=Intent(this, MiembroActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btneliminar.setOnClickListener {
            var m = Miembro(id,b.txtNom.text.toString(),
                b.txtCar.text.toString())
            vistaMiembro=ViewModelProvider(this).get()
            vistaMiembro.eliminar(m)
            val v=Intent(this, MiembroActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }
}