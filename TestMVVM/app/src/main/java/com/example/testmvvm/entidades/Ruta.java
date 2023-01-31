package com.example.testmvvm.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.testmvvm.constantes.Constantes;


@Entity(tableName = Constantes.NOMBRE_TABLA_RUTA,
        //primaryKeys = {"origenId","destinoId"},
        foreignKeys = {
                @ForeignKey(entity = Lugar.class,
                        parentColumns = {"id"},
                        childColumns = {"origenId"},
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Lugar.class,
                        parentColumns = {"id"},
                        childColumns = {"destinoId"},
                        onDelete = ForeignKey.CASCADE)
        })

public class Ruta {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "origenId")
    public int origenId;

    @ColumnInfo(name = "destinoId")
    public int destinoId;

    public Ruta(int lugar1, int lugar2, String nombre) {
        this.origenId = lugar1;
        this.destinoId = lugar2;
        this.nombre = nombre;
    }

    public int getorigenValue() {
        return this.origenId;
    }
    public int getdestinoValue() {
        return this.destinoId;
    }
    public String getNombreValue() {
        return this.nombre;
    }

    public Ruta() {}

}
