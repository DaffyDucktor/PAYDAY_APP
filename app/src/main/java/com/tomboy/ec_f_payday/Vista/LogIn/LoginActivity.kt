package com.tomboy.ec_f_payday.Vista.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.tomboy.ec_f_payday.Vista.Comprador.CompradorActivity
import com.tomboy.ec_f_payday.Vista.Producto
import com.tomboy.ec_f_payday.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var b: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.btnIniciar.setOnClickListener {
            var correo = b.txtCorreo.text.toString()
            var clave = b.txtClave.text.toString()

            if ( correo.isEmpty() == false && clave.isEmpty() == false){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,clave)
                    .addOnCompleteListener {
                        val mensaje=AlertDialog.Builder(this)
                        mensaje.setTitle("Advertencia")
                        if(it.isSuccessful){
                            mensaje.setMessage("Inicio de Sesión Exitoso...")
                            val intent: Intent = Intent(this, Producto:: class.java)
                            startActivity((intent))
                        }else{
                            mensaje.setMessage("Error al iniciar cuenta del usuario...")
                        }
                        mensaje.setPositiveButton("Aceptar", null)
                        val alerta = mensaje.create()
                        alerta.show()
                    }
            }


        }

        b.btnSuscribirse.setOnClickListener {
            var correo = b.txtCorreo.text.toString()
            var clave = b.txtClave.text.toString()

            if ( correo.isEmpty() == false && clave.isEmpty() == false){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo,clave)
                    .addOnCompleteListener {
                        val mensaje=AlertDialog.Builder(this)
                        mensaje.setTitle("Advertencia")
                        if(it.isSuccessful){
                            mensaje.setMessage("Se ha suscrito de forma correcta...")
                        }else{
                            mensaje.setMessage("No logró suscribirse. Error en la grabación...")
                        }
                        mensaje.setPositiveButton("Aceptar", null)
                        val alerta = mensaje.create()
                        alerta.show()
                    }
            }


        }
    }
}