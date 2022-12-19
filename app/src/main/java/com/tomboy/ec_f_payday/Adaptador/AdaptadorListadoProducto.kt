package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.Modelo.Producto
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Atuendo.FrmAtuendo
import com.tomboy.ec_f_payday.databinding.ListaAtuendoBinding
import com.tomboy.ec_f_payday.databinding.ListaProductoBinding

class AdaptadorListadoProducto(private val data: List<Producto>):
    RecyclerView.Adapter<AdaptadorListadoProducto.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ListaProductoBinding = ListaProductoBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(p: Producto) {
            binding.imagen.setImageResource(R.drawable.foto)
            var vinculo = p.imagen
            if(vinculo.length>0){
                Picasso.get().load(vinculo).into(binding.imagen)
            }
            binding.txtCodigo.text = p.codigo
            binding.txtDescripcion.text = p.descripcion
            binding.txtMarca.text = p.marca
            binding.txtPrecioCompra.text = p.preciocompra.toString()
            binding.txtPrecioVenta.text = p.precioventa.toString()
            binding.txtStock.text = p.stock.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_producto,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Producto?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size

}