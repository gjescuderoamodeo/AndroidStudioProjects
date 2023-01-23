package com.example.roomtest3.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtest3.entidades.Lugar;

import java.util.List;

@Dao
public interface InterfaceDaoLugar {

    @Insert
    void crearLugar(Lugar al);

    @Delete
    void borrarLugar(Lugar al);

    @Update
    void modificarLugar(Lugar al);


    @Query("SELECT * FROM Lugar WHERE id LIKE :id")
    Lugar verLugar(int id);

    @Query("SELECT * FROM Lugar WHERE nombre LIKE :nombre")
    Lugar verLugar(String nombre);

    @Query("SELECT * FROM Lugar")
    List<Lugar> verLugar();

    @Query("DELETE FROM Lugar")
    void nukeTable();

}
