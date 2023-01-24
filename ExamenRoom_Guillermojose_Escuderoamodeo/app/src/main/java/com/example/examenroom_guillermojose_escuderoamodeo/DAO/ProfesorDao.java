package com.example.examenroom_guillermojose_escuderoamodeo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Grupo;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Profesor;

import java.util.List;

@Dao
public interface ProfesorDao {
    @Insert
    void insert(Profesor profesor);

    @Delete
    void delete(Profesor profesor);

    @Update
    void update(Profesor profesor);

    @Query("SELECT * FROM Profesor")
    List<Profesor> getAllProfesores();

    @Query("SELECT * FROM profesor WHERE id = :id")
    Profesor getProfesorById(String id);
}
