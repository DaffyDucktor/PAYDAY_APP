package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Atuendo (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var descripcion: String,
    var nombre: String
)
