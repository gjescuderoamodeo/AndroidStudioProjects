package com.example.testmvvm.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.testmvvm.constantes.Constantes;


@Entity(tableName = Constantes.NOMBRE_TABLA_POSICION)
public class Lugar {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "x")
    public int x;

    @ColumnInfo(name = "y")
    public int y;

    @ColumnInfo(name = "nombre")
    public String nombre;

    public Lugar(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        //this.id=0;
    }

    public int getXValue() {
        return this.x;
    }
    public int getId() {
        return this.id;
    }
    public int getYValue() {
        return this.y;
    }

    public String getNombreValue() {
        return this.nombre;
    }

    public Lugar() {}
}