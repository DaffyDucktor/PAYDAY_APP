package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contrato (
    @PrimaryKey(autoGenerate = true)
    var contractId: Long,
    var fecha_entrega: String,
    var nombre: String,
    var precio: String,
    var compradorId: Long,
    var equipoId: Long,
    var imagenId: Long,
    var ateundoId: Long
)
