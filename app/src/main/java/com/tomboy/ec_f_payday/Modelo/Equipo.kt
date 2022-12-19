package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipo (
    @PrimaryKey(autoGenerate = true)
    var equipoId: Long,
    var descripcion: String,
    var nombre: String
)
