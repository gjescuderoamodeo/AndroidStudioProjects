package com.example.roomtestkotlin.modelo.entidades.DAO

import androidx.room.*
import com.example.roomtestkotlin.modelo.entidades.Lugar

@Dao
interface InterfaceDaoCoordenada {
    @Insert
    fun crearLugar(al: Lugar?)

    @Delete
    fun borrarLugar(al: Lugar?)

    @Update
    fun modificarLugar(al: Lugar?)

    @Query("SELECT * FROM Lugar WHERE id LIKE :id")
    fun verLugar(id: Int): Lugar?

    @Query("SELECT * FROM Lugar WHERE nombre LIKE :nombre")
    fun verACoordenada(nombre: String?): Lugar?

    @Query("SELECT * FROM Lugar")
    fun verCoordenada(): List<Lugar?>?

    @Query("DELETE FROM Lugar")
    fun nukeTable()
}