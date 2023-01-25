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
    @Query("SELECT * FROM alumno WHERE alumno.grupoId = :grupoId")
    List<Alumno> getAlumnosByGrupo(int grupoId);

    //Crear un método que devuelva los alumnos matriculados en una asignatura
    //determinada


    //@Query("SELECT a.* FROM Alumno a, Asignatura b, AsignaturaAlumno aa " +
    //        "WHERE aa.alumnoId=a.id AND")
    //List<Alumno> getAlumnosByAsignatura(int asignaturaId);

    @Query("SELECT a.* FROM alumno a INNER JOIN AsignaturaAlumno aa ON aa.alumnoId = a.id " +
            "WHERE aa.asignaturaId = :asignaturaId")
    List<Alumno> getAlumnosByAsignatura(int asignaturaId);

}
