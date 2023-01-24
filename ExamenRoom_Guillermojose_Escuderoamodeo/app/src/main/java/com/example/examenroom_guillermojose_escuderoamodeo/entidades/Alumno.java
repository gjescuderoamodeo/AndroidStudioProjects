package com.example.examenroom_guillermojose_escuderoamodeo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;

import java.util.List;

@Entity(tableName = Constantes.NOMBRE_TABLA_ALUMNO)
public class Alumno {

    //id (int Auto generado), nombre (String), apellido (String)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "apellido")
    public String apellido;

    @Relation(parentColumn = "id", entityColumn = "alumnoId", entity = AsignaturaAlumno.class)
    private List<Asignatura> asignaturas;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
