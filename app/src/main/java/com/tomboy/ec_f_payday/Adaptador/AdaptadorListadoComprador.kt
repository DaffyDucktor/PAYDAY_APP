package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.Modelo.Comprador
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Comprador.FrmComprador
import com.tomboy.ec_f_payday.databinding.ListaCompradorBinding

class AdaptadorListadoComprador(private val data:List<Comprador>?): RecyclerView.Adapter<AdaptadorListadoComprador.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding:ListaCompradorBinding = ListaCompradorBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(c: Comprador) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = c.compradorId.toString()
            binding.txtProfesion.text = c.profesion
            binding.txtNombre.text = c.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmComprador::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", c.compradorId)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_comprador,parent,false)
        return AdaptadorListadoComprador.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Comprador?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size

}