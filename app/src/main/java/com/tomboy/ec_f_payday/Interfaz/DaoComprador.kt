package com.tomboy.ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Comprador

@Dao
interface DaoComprador {

    @Query("SELECT * FROM Comprador")
    fun ListarComprador():List<Comprador>

    @Query("Select * from Comprador where nombre LIKE '%' || :cadena || '%'")
    fun buscanombre(cadena:String):List<Comprador>

    @Query("Select * from Comprador where compradorId = :id")
    fun buscarid(id:Long):Comprador


    @Insert
    fun AgregarComprador(compradores:List<Comprador>):List<Long>

    @Delete
    fun EliminarComprador(comprador: Comprador):Int

    @Update
    fun ActualizarComprador(comprador: Comprador):Int

}