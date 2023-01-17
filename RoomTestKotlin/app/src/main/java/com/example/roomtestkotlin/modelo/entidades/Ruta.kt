package com.example.roomtestkotlin.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.roomtestkotlin.constantes.Constantes

@Entity(tableName = Constantes.NOMBRE_TABLA_RUTA,
    //primaryKeys = {"origenId","destinoId"},
        foreignKeys = [
            ForeignKey(entity = Lugar::class,
                parentColumns = ["id"],
                childColumns = ["origenId"],
                onDelete = CASCADE),
            ForeignKey(entity = Lugar::class,
                parentColumns = ["id"],
                childColumns = ["destinoId"],
                onDelete = CASCADE)
        ])

class Ruta {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "nombre")
    var nombre: String = ""

    @ColumnInfo(name = "origenId")
    var origenId: Long = 0

    @ColumnInfo(name = "destinoId")
    var destinoId: Long = 0

}