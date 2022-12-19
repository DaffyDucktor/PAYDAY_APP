package com.tomboy.ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Miembro

@Dao
interface DaoMiembro {

    @Query("SELECT * FROM Miembro")
    fun ListarMiembro():List<Miembro>

    @Query("Select * from Miembro where nombre LIKE '%' || :cadena || '%'")
    fun buscarnombre(cadena:String):List<Miembro>

    @Query("Select * from Miembro where miembroId = :id")
    fun buscarid(id:Long):Miembro


    @Insert
    fun AgregarMiembro(miembros:List<Miembro>):List<Long>

    @Delete
    fun EliminarMiembro(miembro: Miembro):Int

    @Update
    fun ActualizarMiembro(miembro: Miembro):Int
}