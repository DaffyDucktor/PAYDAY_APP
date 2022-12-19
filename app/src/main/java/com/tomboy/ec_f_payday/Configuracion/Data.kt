package com.tomboy.ec_f_payday.Configuracion

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomboy.ec_f_payday.Interfaz.*
import com.tomboy.ec_f_payday.Modelo.*
import ec_f_payday.Interfaz.DaoContrato


@Database(
    entities = [Atuendo::class, Comprador::class, Contrato::class, Equipo::class, Imagen::class, Miembro::class],
    version = 1
)

abstract class Data:RoomDatabase() {
    abstract fun daoatuendo(): DaoAtuendo
    abstract fun daocomprador(): DaoComprador
    abstract fun daocontrato(): DaoContrato
    abstract fun daoequipo(): DaoEquipo
    abstract fun daoimagen(): DaoImagen
    abstract fun daomiembro(): DaoMiembro
}