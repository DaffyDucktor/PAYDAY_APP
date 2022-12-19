package com.tomboy.ec_f_payday.Modelo


data class Producto(

    var codigo: String = "",
    var descripcion: String = "",
    var marca: String = "",
    var preciocompra: Double = 0.0,
    var precioventa: Double = 0.0,
    var stock: Int = 0,
    var imagen: String = "",


    )
