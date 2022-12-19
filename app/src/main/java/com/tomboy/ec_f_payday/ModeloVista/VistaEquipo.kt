package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Contrato
import com.tomboy.ec_f_payday.Modelo.Equipo
import com.tomboy.ec_f_payday.databinding.ActivityFrmEquipoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaEquipo:ViewModel() {

    val lista = MutableLiveData<List<Equipo>?>()

    fun iniciar() {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoequipo().ListarEquipo()
            }
        }
    }

    fun registrar(e:Equipo) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoequipo().AgregarEquipo(arrayListOf<Equipo>(
                    e
                ))
                db.daoequipo().ListarEquipo()
            }
        }
    }

    fun actualizar(e:Equipo){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoequipo().ActualizarEquipo(e)
            }
        }
    }

    fun eliminar(e:Equipo){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoequipo().EliminarEquipo(e)
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daoequipo().buscarnombre(cadena)
            }
        }
    }

    fun buscarid(id:Long, b: ActivityFrmEquipoBinding){
        viewModelScope.launch {
            val equipo:Equipo = withContext(Dispatchers.IO){
                db.daoequipo().buscarid(id)
            }
            b.txtDesc.setText(equipo.descripcion.toString())
            b.txtNom.setText(equipo.nombre.toString())
        }
    }
}