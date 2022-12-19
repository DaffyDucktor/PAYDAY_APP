package com.tomboy.ec_f_payday.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Miembro (
    @PrimaryKey(autoGenerate = true)
    var miembroId: Long,
    var cargo: String,
    var nombre: String
)
