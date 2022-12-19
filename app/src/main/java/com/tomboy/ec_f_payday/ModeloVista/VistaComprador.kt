package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Atuendo
import com.tomboy.ec_f_payday.Modelo.Comprador
import com.tomboy.ec_f_payday.databinding.ActivityFrmCompradorBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaComprador:ViewModel() {
    val lista=MutableLiveData<List<Comprador>?>()

    fun iniciar(){
        viewModelScope.launch {
            lista.value = withContext((Dispatchers.IO)) {
                db.daocomprador().ListarComprador()
            }
        }
    }

    fun registrar(c:Comprador) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daocomprador().AgregarComprador(arrayListOf<Comprador>(
                    c
                ))
                db.daocomprador().ListarComprador()
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daocomprador().buscanombre(cadena)
            }
        }
    }

    fun buscarid(id:Long, b: ActivityFrmCompradorBinding){
        viewModelScope.launch {
            val comprador: Comprador = withContext(Dispatchers.IO){
                db.daocomprador().buscarid(id)
            }
            b.txtPro.setText(comprador.profesion.toString())
            b.txtNom.setText(comprador.nombre.toString())
        }
    }

    fun actualizar(c:Comprador){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daocomprador().ActualizarComprador(c)
            }
        }
    }

    fun eliminar(c:Comprador){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daocomprador().EliminarComprador(c)
            }
        }
    }
}