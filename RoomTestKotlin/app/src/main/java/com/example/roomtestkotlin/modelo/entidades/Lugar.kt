package com.example.roomtestkotlin.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomtestkotlin.constantes.Constantes

@Entity(tableName = Constantes.NOMBRE_TABLA_POSICION) /*@Entity(tableName = "rutas",
    //caso primary key compuesta
        PrimaryKeys{"id_lugar","id_aaa"},

    //  foreignKeys compuesta
        foreignKeys = {
                @ForeignKey(entity = Lugar.class,
                        parentColumns = "id_lugar",
                        childColumns = "origen",
                        OnDelete=CASCADE),                @ForeignKey(entity = Lugar.class,
                        parentColumns = "id_lugar",
                        childColumns = "destino")
        }
)*/
class Lugar {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "x")
    var x = 0

    @ColumnInfo(name = "y")
    var y = 0

    @ColumnInfo(name = "nombre")
    var nombre: String? = null

    constructor(x: Int, y: Int, nombre: String?) {
        this.x = x
        this.y = y
        this.nombre = nombre
    }

    fun getXValue(): Int {
        return this.x
    }

    fun getYValue(): Int {
        return this.y
    }

    fun getNombreValue(): String? {
        return this.nombre
    }



    constructor() {}
}