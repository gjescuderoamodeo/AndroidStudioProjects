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

    @Query("DELETE FROM Alumno")
    void nukeTable();

    @Query("SELECT * FROM alumno")
    List<Alumno> getAllAlumnos();

    @Query("SELECT * FROM alumno WHERE id = :id")
    Alumno getAlumnoById(int id);

    //método en Dao de Alumnos que devuelva los alumnos de un Grupo
    //determinado
    @Query("SELECT * FROM alumno WHERE grupo = :grupoId")
    List<Alumno> getAlumnosByGrupo(int grupoId);

    //Crear un método que devuelva los alumnos matriculados en una asignatura
    //determinada
    @Query("SELECT * FROM alumno a, Asignatura by WHERE asignaturas = :grupoId")
    List<Alumno> getAlumnosByGrupo(int grupoId);

}
