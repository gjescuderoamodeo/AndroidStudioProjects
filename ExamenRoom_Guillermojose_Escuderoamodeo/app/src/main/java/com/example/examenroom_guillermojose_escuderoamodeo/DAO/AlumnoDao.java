package com.example.examenroom_guillermojose_escuderoamodeo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Alumno;

import java.util.List;

@Dao
public interface AlumnoDao {
    @Insert
    void insert(Alumno alumno);

    @Delete
    void delete(Alumno alumno);

    @Update
    void update(Alumno alumno);

    @Query("SELECT * FROM alumno")
    List<Alumno> getAllAlumnos();

    @Query("SELECT * FROM alumno WHERE id = :id")
    Alumno getAlumnoById(int id);
}
