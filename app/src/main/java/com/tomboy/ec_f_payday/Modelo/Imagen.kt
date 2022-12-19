package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Imagen (
    @PrimaryKey(autoGenerate = true)
    var imagenId: Long,
    var file: String,
    var nombre: String
)
