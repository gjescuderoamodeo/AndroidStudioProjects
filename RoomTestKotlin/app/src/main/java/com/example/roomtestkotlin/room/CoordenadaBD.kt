package com.example.roomtestkotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomtestkotlin.modelo.entidades.DAO.InterfaceDaoCoordenada
import com.example.roomtestkotlin.constantes.Constantes
import com.example.roomtestkotlin.modelo.entidades.Lugar
import com.example.roomtestkotlin.modelo.entidades.Ruta

@Database(entities = [Lugar::class, Ruta::class], version = 1)
abstract class CoordenadaBD : RoomDatabase() {
    abstract fun daoCoordenada(): InterfaceDaoCoordenada?
    fun destroyInstance() {
        INSTANCE = null
    }

    companion object {
        var INSTANCE: CoordenadaBD? = null
        fun getCoordenadaBD(context: Context): CoordenadaBD? {
            if (INSTANCE == null) {
                //Usar allowMainThreadQueries() solo para pruebas
                INSTANCE = Room.databaseBuilder<CoordenadaBD>(
                    context.applicationContext, CoordenadaBD::class.java,
                    Constantes.NOMBRE_DB
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}