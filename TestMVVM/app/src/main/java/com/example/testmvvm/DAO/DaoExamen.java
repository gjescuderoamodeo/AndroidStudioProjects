package com.example.testmvvm.DAO;

import com.example.testmvvm.entidades.Lugar;

import java.util.List;

public interface DaoExamen {
    public interface InterfaceDaoLugar {

        Lugar verLugar(int id);

        Lugar verLugar(String nombre);

        List<Lugar> verLugar();

        void nukeTable();

    }

}
