package com.tomboy.ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Imagen

@Dao
interface DaoImagen {

    @Query("SELECT * FROM Imagen")
    fun ListarImagen():List<Imagen>

    @Query("Select * from Imagen where nombre LIKE '%' || :cadena || '%'")
    fun buscarnombre(cadena:String):List<Imagen>

    @Query("Select * from Imagen where imagenId = :id")
    fun buscarid(id:Long):Imagen

    @Insert
    fun AgregarImagen(imagenes:List<Imagen>):List<Long>

    @Delete
    fun EliminarImagen(imagen: Imagen):Int

    @Update
    fun ActualizarImagen(imagen: Imagen):Int
}