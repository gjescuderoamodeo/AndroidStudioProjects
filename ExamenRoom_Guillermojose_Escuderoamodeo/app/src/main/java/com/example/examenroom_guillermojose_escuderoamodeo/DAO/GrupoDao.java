package com.example.examenroom_guillermojose_escuderoamodeo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Grupo;

import java.util.List;

@Dao
public interface GrupoDao {
    @Insert
    void insert(Grupo grupo);

    @Delete
    void delete(Grupo grupo);

    @Update
    void update(Grupo grupo);

    @Query("SELECT * FROM grupo")
    List<Grupo> getAllGrupos();

    @Query("SELECT * FROM grupo WHERE id = :id")
    Grupo getGrupoById(String id);
}

