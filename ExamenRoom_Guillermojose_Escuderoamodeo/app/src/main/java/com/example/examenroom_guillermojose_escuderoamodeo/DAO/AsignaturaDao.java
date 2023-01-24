package com.example.examenroom_guillermojose_escuderoamodeo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Asignatura;

import java.util.List;

@Dao
public interface AsignaturaDao {
    @Insert
    void insert(Asignatura asignatura);

    @Delete
    void delete(Asignatura asignatura);

    @Update
    void update(Asignatura asignatura);

    @Query("DELETE FROM Asignatura")
    void nukeTable();

    @Query("SELECT * FROM asignatura")
    List<Asignatura> getAllAsignaturas();

    @Query("SELECT * FROM asignatura WHERE id = :id")
    Asignatura getAsignaturaById(int id);
}

