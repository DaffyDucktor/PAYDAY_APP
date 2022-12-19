package com.tomboy.ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Equipo

@Dao
interface DaoEquipo {

    @Query("SELECT * FROM Equipo")
    fun ListarEquipo():List<Equipo>

    @Query("Select * from Equipo where nombre LIKE '%' || :cadena || '%'")
    fun buscarnombre(cadena:String):List<Equipo>

    @Query("Select * from Equipo where equipoId = :id")
    fun buscarid(id:Long):Equipo


    @Insert
    fun AgregarEquipo(equipos:List<Equipo>):List<Long>

    @Delete
    fun EliminarEquipo(equipo: Equipo):Int

    @Update
    fun ActualizarEquipo(equipo: Equipo):Int
}