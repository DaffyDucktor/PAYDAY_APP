package com.tomboy.ec_f_payday.ModeloVista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomboy.ec_f_payday.Configuracion.AppData.Companion.db
import com.tomboy.ec_f_payday.Modelo.Comprador
import com.tomboy.ec_f_payday.Modelo.Contrato
import com.tomboy.ec_f_payday.databinding.ActivityFrmContratoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaContrato:ViewModel() {

    val lista = MutableLiveData<List<Contrato>?>()

    fun iniciar(){
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daocontrato().ListarContrato()
            }
        }
    }

    fun registrar(c:Contrato) {
        viewModelScope.launch {
            lista.value = withContext(Dispatchers.IO) {
                db.daocontrato().AgregarContrato(arrayListOf<Contrato>(
                    c
                ))
                db.daocontrato().ListarContrato()
            }
        }
    }

    fun buscarnombre(cadena:String){
        viewModelScope.launch {
            lista.value= withContext(Dispatchers.IO){
                db.daocontrato().buscarnombre(cadena)
            }
        }
    }

    fun buscarid(cod:Long, b: ActivityFrmContratoBinding){
        viewModelScope.launch {
            val contrato:Contrato = withContext(Dispatchers.IO){
                db.daocontrato().buscarid(cod)
            }
            b.txtNom.setText(contrato.nombre.toString())
            b.txtFec.setText(contrato.fecha_entrega.toString())
            b.txtPre.setText(contrato.precio.toString())
        }
    }

    fun actualizar(c: Contrato){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daocontrato().ActualizarContrato(c)
            }
        }
    }

    fun eliminar(c: Contrato){
        viewModelScope.launch {
            var resp= withContext(Dispatchers.IO){
                db.daocontrato().EliminarContrato(c)
            }
        }
    }
}