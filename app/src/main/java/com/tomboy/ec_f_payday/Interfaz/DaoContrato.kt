package ec_f_payday.Interfaz

import androidx.room.*
import com.tomboy.ec_f_payday.Modelo.Contrato

@Dao
interface DaoContrato {

    @Query("SELECT * FROM Contrato")
    fun ListarContrato():List<Contrato>

    @Query("Select * from Contrato where nombre LIKE '%' || :cadena || '%'")
    fun buscarnombre(cadena:String):List<Contrato>

    @Query("Select * from Contrato where contractId = :id")
    fun buscarid(id:Long):Contrato

    @Insert
    fun AgregarContrato(contratos:List<Contrato>):List<Long>

    @Delete
    fun EliminarContrato(contrato: Contrato):Int

    @Update
    fun ActualizarContrato(contrato: Contrato):Int
}