package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Equipo
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Equipo.FrmEquipo
import com.tomboy.ec_f_payday.databinding.ListaEquipoBinding

class AdaptadorListadoEquipo(private val data:List<Equipo>?): RecyclerView.Adapter<AdaptadorListadoEquipo.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ListaEquipoBinding = ListaEquipoBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(e: Equipo) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = e.equipoId.toString()
            binding.txtDescripcion.text = e.descripcion
            binding.txtNombre.text = e.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmEquipo::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", e.equipoId)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_equipo,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Equipo?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size

}