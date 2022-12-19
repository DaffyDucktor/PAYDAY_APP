package com.tomboy.ec_f_payday.Vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.tomboy.ec_f_payday.Adaptador.AdaptadorListadoProducto
import com.tomboy.ec_f_payday.Modelo.Producto
import com.tomboy.ec_f_payday.databinding.ActivityListarProductoBinding

class ListarProductoActivity : AppCompatActivity() {

    val db= FirebaseFirestore.getInstance()

    lateinit var binding: ActivityListarProductoBinding

    private lateinit var adapterProducto: AdaptadorListadoProducto
    private lateinit var productoList: ArrayList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarProductoBinding.inflate(layoutInflater)

        setContentView(binding.root)


        llamarrecyclerview()
    }


    private fun llamarrecyclerview() {
        productoList = ArrayList()
        adapterProducto = AdaptadorListadoProducto((productoList))

        db.collection("Productos")
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    val wallItem = document.toObject(Producto::class.java)

                    wallItem.codigo = document["codigo"].toString()
                    wallItem.descripcion = document["descripcion"].toString()
                    wallItem.imagen = document["imagen"].toString()
                    wallItem.precioventa = Integer.parseInt(document["precioventa"].toString()).toDouble()
                    wallItem.preciocompra = Integer.parseInt(document["preciocompra"].toString()).toDouble()
                    wallItem.stock = Integer.parseInt(document["stock"].toString())
                    wallItem.marca = document["marca"].toString()

                    binding.listado.adapter = adapterProducto
                    binding.listado.layoutManager = LinearLayoutManager(this)
                    productoList.add(wallItem)

                }
            }
    }
}
