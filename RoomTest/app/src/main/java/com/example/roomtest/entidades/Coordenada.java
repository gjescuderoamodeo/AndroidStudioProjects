package com.example.roomtest.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.roomtest.constantes.Constantes;

@Entity(tableName= Constantes.NOMBRE_TABLA_POSICION)



/*@Entity(tableName = "rutas",
    //caso primary key compuesta
        PrimaryKeys{"id_lugar","id_aaa"},

    //  foreignKeys compuesta
        foreignKeys = {
                @ForeignKey(entity = Lugar.class,
                        parentColumns = "id_lugar",
                        childColumns = "origen",
                        OnDelete=CASCADE),
                @ForeignKey(entity = Lugar.class,
                        parentColumns = "id_lugar",
                        childColumns = "destino")
        }
)*/


public class Coordenada {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="x")
    private int x;
    @ColumnInfo(name="y")
    private int y;
    @ColumnInfo(name="nombre")
    private String nombre;

    public Coordenada(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre=nombre;
    }

    public Coordenada() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
