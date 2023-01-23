package com.example.roomtest3.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtest3.entidades.Lugar;
import com.example.roomtest3.entidades.Ruta;

import java.util.List;

@Dao
public interface InterfaceDaoRuta {

    @Insert
    void crearRuta(Ruta al);

    @Delete
    void borrarRuta(Ruta al);

    @Update
    void modificarRuta(Ruta al);


    @Query("SELECT * FROM Ruta WHERE id LIKE :id")
    Ruta verRuta(int id);

    @Query("SELECT * FROM Ruta WHERE nombre LIKE :nombre")
    Ruta verRuta(String nombre);

    @Query("SELECT * FROM Ruta")
    List<Ruta> verRuta();

    @Query("DELETE FROM Ruta")
    void nukeTable();

}
