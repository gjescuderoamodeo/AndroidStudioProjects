package com.example.examenroom_guillermojose_escuderoamodeo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.AsignaturaAlumno;

import java.util.List;

@Dao
public interface AsignaturaAlumnoDao {
    @Insert
    void insert(AsignaturaAlumno asignaturaAlumno);

    @Delete
    void delete(AsignaturaAlumno asignaturaAlumno);

    @Update
    void update(AsignaturaAlumno asignaturaAlumno);

    @Query("SELECT * FROM AsignaturaAlumno WHERE alumnoId = :alumnoId")
    List<AsignaturaAlumno> getAsignaturasByAlumnoId(int alumnoId);

    @Query("SELECT * FROM AsignaturaAlumno WHERE asignaturaId = :asignaturaId")
    List<AsignaturaAlumno> getAlumnosByAsignaturaId(int asignaturaId);

    @Query("DELETE FROM AsignaturaAlumno")
    void nukeTable();
}

