package com.tomboy.ec_f_payday.Vista.Imagen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tomboy.ec_f_payday.MainActivity
import com.tomboy.ec_f_payday.Modelo.Equipo
import com.tomboy.ec_f_payday.Modelo.Imagen
import com.tomboy.ec_f_payday.ModeloVista.VistaImagen
import com.tomboy.ec_f_payday.Vista.Equipo.EquipoActivity
import com.tomboy.ec_f_payday.databinding.ActivityFrmImagenBinding

class FrmImagen : AppCompatActivity() {
    lateinit var b: ActivityFrmImagenBinding
    lateinit var vistaImagen: VistaImagen
    var id: Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityFrmImagenBinding.inflate(layoutInflater)

        setContentView(b.root)
        var datos = intent.extras
        var op = datos!!.getInt("op").toInt()
        b.btnGuardar.visibility = View.VISIBLE
        if(op==1){
            b.btnGuardar.visibility = View.VISIBLE
        }
        if(op==2){
            id = datos.getLong("id").toLong()
            vistaImagen = ViewModelProvider(this).get()
            vistaImagen.buscarid(id,b)
        }

        b.btnGuardar.setOnClickListener {
            var i = Imagen(0, b.txtFile.text.toString(),
                b.txtNom.text.toString())

            vistaImagen = ViewModelProvider(this).get()
            vistaImagen.registrar(i)

            val v = Intent(this, MainActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btnactualizar.setOnClickListener {
            var i = Imagen(id,b.txtNom.text.toString(),
                b.txtFile.text.toString())
            vistaImagen=ViewModelProvider(this).get()
            vistaImagen.actualizar(i)
            val v=Intent(this, ImagenActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }

        b.btneliminar.setOnClickListener {
            var i = Imagen(id,b.txtNom.text.toString(),
                b.txtFile.text.toString())
            vistaImagen=ViewModelProvider(this).get()
            vistaImagen.eliminar(i)
            val v=Intent(this, ImagenActivity::class.java)
            v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(v)
        }
    }
}