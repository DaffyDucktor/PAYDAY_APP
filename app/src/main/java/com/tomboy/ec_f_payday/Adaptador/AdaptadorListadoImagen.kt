package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Imagen
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Imagen.FrmImagen
import com.tomboy.ec_f_payday.databinding.ListaImagenBinding

class AdaptadorListadoImagen(private val data:List<Imagen>?): RecyclerView.Adapter<AdaptadorListadoImagen.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ListaImagenBinding = ListaImagenBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(i: Imagen) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = i.imagenId.toString()
            binding.txtFile.text = i.file
            binding.txtNombre.text = i.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmImagen::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", i.imagenId)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_imagen,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:Imagen?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size

}