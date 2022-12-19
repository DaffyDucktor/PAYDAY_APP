package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Equipo
import com.tomboy.ec_f_payday.Modelo.Imagen
import com.tomboy.ec_f_payday.databinding.ActivityFrmImagenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaImagen:ViewModel() {

    val lista = MutableLiveData<List<Imagen>?>()

    fun iniciar() {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoimagen().ListarImagen()
            }
        }
    }

    fun registrar(i:Imagen) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daoimagen().AgregarImagen(arrayListOf<Imagen>(
                    i
                ))
                db.daoimagen().ListarImagen()
            }
        }
    }

    fun actualizar(i:Imagen){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoimagen().ActualizarImagen(i)
            }
        }
    }

    fun eliminar(i:Imagen){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daoimagen().EliminarImagen(i)
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daoimagen().buscarnombre(cadena)
            }
        }
    }

    fun buscarid(cod:Long, b: ActivityFrmImagenBinding){
        viewModelScope.launch {
            val imagen:Imagen = withContext(Dispatchers.IO){
                db.daoimagen().buscarid(cod)
            }
            b.txtFile.setText(imagen.file.toString())
            b.txtNom.setText(imagen.nombre.toString())
        }
    }
}