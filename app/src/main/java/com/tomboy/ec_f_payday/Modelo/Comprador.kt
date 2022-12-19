package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comprador (
    @PrimaryKey(autoGenerate = true)
    var compradorId: Long,
    var nombre: String,
    var profesion: String
)
