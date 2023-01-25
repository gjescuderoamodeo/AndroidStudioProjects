package com.example.examenroom_guillermojose_escuderoamodeo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;

import java.util.List;

@Entity(tableName = Constantes.NOMBRE_TABLA_ALUMNO,
        foreignKeys = {
                @ForeignKey(entity = Grupo.class,
                        parentColumns = {"grupoId"},
                        childColumns = {"grupoId"},
                        onDelete = ForeignKey.CASCADE),
        })
public class Alumno {

    //id (int Auto generado), nombre (String), apellido (String)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "apellido")
    public String apellido;

    //@Relation(parentColumn = "id", entityColumn = "alumnoId", entity = AsignaturaAlumno.class)
    //private List<Asignatura> asignaturas;

    //@Relation(parentColumn = "id", entityColumn = "grupoId", entity = Grupo.class)
    @ColumnInfo(name = "grupoId",index = true)
    private String grupoId;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String grupoId) {
        this.nombre = nombre;
        this.apellido = apellido;
        //this.asignaturas = asignaturas;
        this.grupoId = grupoId;
    }

    //public List<Asignatura> getAsignaturas() {
    //    return asignaturas;
    //}

    //public void setAsignaturas(List<Asignatura> asignaturas) {
    //    this.asignaturas = asignaturas;
    //}


    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
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
