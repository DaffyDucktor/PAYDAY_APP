package com.tomboy.ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Atuendo

@Dao
interface DaoAtuendo {

    @Query("SELECT * FROM Atuendo")
    fun ListarAtuendo():List<Atuendo>

    @Query("Select * from Atuendo where nombre LIKE '%' || :cadena || '%'")
    fun buscarnombre(cadena:String):List<Atuendo>

    @Query("Select * from Atuendo where id = :id")
    fun buscarid(id:Long):Atuendo


    @Insert
    fun AgregarAtuendo(atuendos:List<Atuendo>):List<Long>

    @Delete
    fun EliminarAtuendo(atuendo:Atuendo):Int

    @Update
    fun ActualizarAtuendo(atuendo:Atuendo):Int

}