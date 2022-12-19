package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.Modelo.Producto
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Atuendo.FrmAtuendo
import com.tomboy.ec_f_payday.databinding.ListaAtuendoBinding

class AdaptadorListadoAtuendo(private val data: List<Atuendo>?): RecyclerView.Adapter<AdaptadorListadoAtuendo.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding:ListaAtuendoBinding = ListaAtuendoBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(a: Atuendo) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = a.id.toString()
            binding.txtDescripcion.text = a.descripcion
            binding.txtNombre.text = a.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmAtuendo::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", a.id)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_atuendo,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:Atuendo?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size


}