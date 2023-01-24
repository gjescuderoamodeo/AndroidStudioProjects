package com.example.examenroom_guillermojose_escuderoamodeo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;

@Entity(tableName = Constantes.NOMBRE_TABLA_ASIGNATURA_ALUMNO, primaryKeys = {"alumnoId", "asignaturaId"})
public class AsignaturaAlumno {

    @ColumnInfo(name = "alumnoId")
    private int alumnoId;

    @ColumnInfo(name = "asignaturaId")
    private int asignaturaId;

    public AsignaturaAlumno(int alumnoId, int asignaturaId) {
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }
}

