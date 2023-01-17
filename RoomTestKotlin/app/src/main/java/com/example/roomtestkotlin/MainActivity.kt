package com.example.roomtestkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.roomtestkotlin.modelo.entidades.Lugar
import com.example.roomtestkotlin.room.CoordenadaBD

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testRoom()
    }

    fun testRoom() {
        //CoordenadaBD.getCoordenadaBD(applicationContext)?.daoCoordenada()?.nukeTable()
        val Lugar = Lugar(1, 2, "sevilla")
        val Lugar2 = Lugar(3, 4, "Cadiz")
        //no funciona si está en el hilo principal a no ser que lo especifiquemos
        CoordenadaBD.getCoordenadaBD(applicationContext)?.daoCoordenada()?.crearLugar(Lugar)
        CoordenadaBD.getCoordenadaBD(applicationContext)?.daoCoordenada()?.crearLugar(Lugar2)
        val lugares: List<Lugar> = CoordenadaBD.getCoordenadaBD(
            applicationContext
        )?.daoCoordenada()?.verCoordenada() as List<Lugar>
        Log.d("lugaresAPP", "hola mundo")
        for (al in lugares) {
            Log.d("lugaresAPP", al.getNombreValue() + " ")
        }
    }
}