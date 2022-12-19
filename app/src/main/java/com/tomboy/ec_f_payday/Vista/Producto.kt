package com.tomboy.ec_f_payday.Vista

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Atuendo.AtuendoActivity
import com.tomboy.ec_f_payday.Vista.LogIn.LoginActivity
import com.tomboy.ec_f_payday.databinding.ActivityProductoBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap

class Producto : AppCompatActivity() {
    private val scope = MainScope()
    private lateinit var b: ActivityProductoBinding
    private lateinit var dialog: AlertDialog.Builder
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dbs: FirebaseStorage
    private lateinit var selectedImg : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProductoBinding.inflate(layoutInflater)
        setContentView(b.root)




        db=FirebaseFirestore.getInstance()
        dbs = FirebaseStorage.getInstance()

        //-- BUSQUEDA

        b.btnProdCodBuscar.setOnClickListener {
            val v:AlertDialog.Builder=AlertDialog.Builder(this)
            v.setTitle(("Ingresar Codigo"))
            val entrada=EditText(this)
            entrada.setHint("Ingrese Codigo")
            entrada.inputType=InputType.TYPE_CLASS_TEXT
            v.setView(entrada)
            v.setPositiveButton("OK", DialogInterface.OnClickListener {dialogInterface, i ->
                if(entrada.text.length>0){
                    db.collection("Productos")
                        .whereEqualTo("codigo", entrada.text.toString())
                        .get()
                        .addOnSuccessListener {
                            documents ->
                            b.txtProdDesc.setText("")
                            b.txtProdCodigo.setText("")
                            b.txtProdMarca.setText("")
                            b.txtProdPrcCompra.setText("")
                            b.txtProdPrcVenta.setText("")
                            b.txtProdStock.setText("")
                            var vinculo =  "https://icon-icons.com/es/icono/imagen-imagen/143003"
                            if(vinculo.length>0){
                                Picasso.get().load(vinculo).into(b.imgProdFoto)
                            }
                            var sw=0
                            for (doc in documents){
                                b.txtProdDesc.setText(doc.data["descripcion"].toString())
                                b.txtProdCodigo.setText(doc.data["codigo"].toString())
                                b.txtProdMarca.setText(doc.data["marca"].toString())
                                b.txtProdPrcCompra.setText(doc.data["preciocompra"].toString())
                                b.txtProdPrcVenta.setText(doc.data["precioventa"].toString())
                                b.txtProdStock.setText(doc.data["stock"].toString())

                                var vinculo = doc.data["imagen"].toString()
                                if(vinculo.toString().length>0){
                                    Picasso.get().load(vinculo).into(b.imgProdFoto)
                                }
                                sw=1
                                break
                            }
                            if(sw == 0){
                                Toast.makeText(this, "Producto no Existe", Toast.LENGTH_LONG).show()
                            }
                        }
                }

            })
            v.show()
        }

        //NUEVO REGISTRO

        b.btnProdNuevo.setOnClickListener {
            limpiarForm()
        }


        //FOTO

        b.btnProdFoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }




        //GUARDAR

        b.btnProdGuardar.setOnClickListener{
                uploadData(it)

        }

        //SALIR

        b.btnProdSignOut.setOnClickListener{
            Firebase.auth.signOut()
            val intent: Intent = Intent(this, LoginActivity:: class.java)
            startActivity((intent))
        }

        //LISTAR

        b.btnProdListar.setOnClickListener {
            val intent: Intent = Intent(this, ListarProductoActivity:: class.java)
            startActivity((intent))

        }


    }



    private fun enviarMensaje(vista : View, mensaje:String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }



    //IMAGEN++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null){
            if(data.data != null){
                selectedImg = data.data!!
                b.imgProdFoto.setImageURI(selectedImg)
            }
        }
    }

    private fun uploadData(view: View){
        val reference = dbs.reference.child("fotos/*").child(Date().time.toString())
        reference.putFile(selectedImg).addOnCompleteListener() {
            if(it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString(), view)
                }
            }
        }
    }

    private fun uploadInfo(imgUrl: String, view: View){
        var codigo = b.txtProdCodigo.text.toString()
        var descripcion = b.txtProdDesc.text.toString()
        var marca = b.txtProdMarca.text.toString()
        var preciocompra = (b.txtProdPrcCompra.text.toString())
        var precioventa = (b.txtProdPrcVenta.text.toString())
        var stock = (b.txtProdStock.text.toString())

        if(codigo.isNotEmpty() && descripcion.isNotEmpty() && marca.isNotEmpty() && preciocompra.isNotEmpty() && precioventa.isNotEmpty() && stock.isNotEmpty()) {
            val producto = hashMapOf(
                "codigo" to codigo,
                "descripcion" to descripcion,
                "marca" to marca,
                "preciocompra" to Integer.parseInt(preciocompra),
                "precioventa" to Integer.parseInt(precioventa),
                "stock" to Integer.parseInt(stock),
                "imagen" to imgUrl
            )



            db.collection("Productos")
            .add(producto)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_LONG).show()
                limpiarForm()

            }
            .addOnFailureListener{

            }
        }
        else if(selectedImg == null){
            enviarMensaje(view, "Inserte una foto")
        }
        else{
            enviarMensaje(view, "Ingrese todos los datos")
        }
    }


    private fun limpiarForm(){
        b.txtProdDesc.setText(null)
        b.txtProdCodigo.setText(null)
        b.txtProdMarca.setText(null)
        b.txtProdPrcCompra.setText(null)
        b.txtProdPrcVenta.setText(null)
        b.txtProdStock.setText(null)
        var vinculo = "https://icon-icons.com/es/icono/imagen-imagen/143003"
        if(vinculo.length>0){
            Picasso.get().load(vinculo).into(b.imgProdFoto)
        }
    }
}