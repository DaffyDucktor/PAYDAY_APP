package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Miembro
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Miembro.FrmMiembro
import com.tomboy.ec_f_payday.databinding.ListaMiembroBinding

class AdaptadorListadoMiembro(private val data:List<Miembro>?): RecyclerView.Adapter<AdaptadorListadoMiembro.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ListaMiembroBinding = ListaMiembroBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(m: Miembro) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = m.miembroId.toString()
            binding.txtCargo.text = m.cargo
            binding.txtNombre.text = m.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmMiembro::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", m.miembroId)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_miembro,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Miembro?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size

}