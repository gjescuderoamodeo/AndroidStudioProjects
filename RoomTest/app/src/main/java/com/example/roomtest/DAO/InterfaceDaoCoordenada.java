package com.example.roomtest.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtest.entidades.Coordenada;

import java.util.List;

@Dao
public interface InterfaceDaoCoordenada {

    @Insert
    public void crearCoordenada(Coordenada al);
    @Delete
    public void borrarCoordenada(Coordenada al);
    @Update
    public void modificarCoordenada(Coordenada al);
    @Query("SELECT * FROM coordenada WHERE id LIKE :id")
    public Coordenada verCoordenada(int id);
    @Query("SELECT * FROM Coordenada WHERE nombre LIKE :nombre")
    public Coordenada verACoordenada(String nombre);
    @Query("SELECT * FROM Coordenada")
    public List<Coordenada> verCoordenada();

    @Query("DELETE FROM coordenada")
    public void nukeTable();
}
