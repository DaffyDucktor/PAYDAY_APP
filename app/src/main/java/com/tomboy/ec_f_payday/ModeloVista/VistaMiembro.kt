package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Imagen
import com.tomboy.ec_f_payday.Modelo.Miembro
import com.tomboy.ec_f_payday.databinding.ActivityFrmMiembroBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaMiembro:ViewModel() {

    val lista = MutableLiveData<List<Miembro>?>()

    fun iniciar() {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daomiembro().ListarMiembro()
            }
        }
    }

    fun registrar(m:Miembro) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daomiembro().AgregarMiembro(arrayListOf<Miembro>(
                    m
                ))
                db.daomiembro().ListarMiembro()
            }
        }
    }
    fun actualizar(m:Miembro){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daomiembro().ActualizarMiembro(m)
            }
        }
    }

    fun eliminar(m:Miembro){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daomiembro().EliminarMiembro(m)
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daomiembro().buscarnombre(cadena)
            }
        }
    }

    fun buscarid(cod:Long, b: ActivityFrmMiembroBinding){
        viewModelScope.launch {
            val miembro:Miembro = withContext(Dispatchers.IO){
                db.daomiembro().buscarid(cod)
            }
            b.txtCar.setText(miembro.cargo.toString())
            b.txtNom.setText(miembro.nombre.toString())
        }
    }
}