package com.example.examenroom_guillermojose_escuderoamodeo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;

import java.util.List;

@Entity(tableName = Constantes.NOMBRE_TABLA_ASIGNATURA)
public class Asignatura {

    //Asignatura (id (int Auto generado), nombre (String))
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    //@Relation(parentColumn = "id", entityColumn = "asignaturaId", entity = AsignaturaAlumno.class)
    //private List<Alumno> alumnos;

    public Asignatura() {
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
        //this.alumnos = alumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
