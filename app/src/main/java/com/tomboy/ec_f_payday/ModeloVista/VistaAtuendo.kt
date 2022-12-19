package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.databinding.ActivityFrmAtuendoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaAtuendo:ViewModel() {

    val lista=MutableLiveData<List<Atuendo>?>()
    fun iniciar() {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoatuendo().ListarAtuendo()
            }
        }
    }

    fun registrar(a:Atuendo) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoatuendo().AgregarAtuendo(arrayListOf<Atuendo>(
                    a
                ))
                db.daoatuendo().ListarAtuendo()
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daoatuendo().buscarnombre(cadena)
            }
        }
    }

    fun buscarid(id:Long, b: ActivityFrmAtuendoBinding){
        viewModelScope.launch {
            val atuendo:Atuendo = withContext(Dispatchers.IO){
                db.daoatuendo().buscarid(id)
            }
            b.txtDesc.setText(atuendo.descripcion.toString())
            b.txtNom.setText(atuendo.nombre.toString())
        }
    }

    fun actualizar(a:Atuendo){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoatuendo().ActualizarAtuendo(a)
            }
        }
    }

    fun eliminar(a:Atuendo){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoatuendo().EliminarAtuendo(a)
            }
        }
    }

}