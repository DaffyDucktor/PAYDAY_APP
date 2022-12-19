package com.tomboy.ec_f_payday.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomboy.ec_f_payday.Modelo.Contrato
import com.tomboy.ec_f_payday.R
import com.tomboy.ec_f_payday.Vista.Contrato.FrmContrato
import com.tomboy.ec_f_payday.databinding.ListaContratoBinding

class AdaptadorListadoContrato(private val data:List<Contrato>?): RecyclerView.Adapter<AdaptadorListadoContrato.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ListaContratoBinding = ListaContratoBinding.bind(view)
        var contexto: Context = view.context
        fun enlazarDatos(c: Contrato) {
            binding.foto.setImageResource(R.drawable.foto)
            binding.txtId.text = c.contractId.toString()
            binding.txtFecha.text = c.fecha_entrega
            binding.txtPrecio.text = c.precio
            binding.txtNombre.text = c.nombre
            binding.root.setOnClickListener {
                val v = Intent(contexto, FrmContrato::class.java)
                v.putExtra("op", 2)
                v.putExtra("id", c.contractId)
                contexto.startActivity(v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_contrato,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Contrato?=data?.get(position)
        holder.enlazarDatos(item!!)
    }

    override fun getItemCount()=data!!.size
}