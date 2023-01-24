package com.example.examenroom_guillermojose_escuderoamodeo.entidades;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;

import java.util.List;

@Entity(tableName = Constantes.NOMBRE_TABLA_GRUPO)
public class Grupo {

    //Grupo (id (String), nombre (String), aula (String))
    @PrimaryKey()
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "aula")
    public String aula;

    @Relation(parentColumn = "id", entityColumn = "grupoId", entity = Alumno.class)
    private List<Alumno> alumnos;

    public Grupo(String id, String nombre, String aula) {
        this.id = id;
        this.nombre = nombre;
        this.aula = aula;
    }

    public Grupo(String id, String nombre, String aula, List<Alumno> alumnos) {
        this.id = id;
        this.nombre = nombre;
        this.aula = aula;
        this.alumnos = alumnos;
    }

    public Grupo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
