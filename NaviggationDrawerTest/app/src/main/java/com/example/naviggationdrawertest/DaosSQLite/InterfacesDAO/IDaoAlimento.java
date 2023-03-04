package com.example.naviggationdrawertest.DaosSQLite.InterfacesDAO;

import com.example.naviggationdrawertest.modelo.Alimento;

import java.util.ArrayList;

public interface IDaoAlimento {

    public void crearAlimento(Alimento dep);
    public void eliminarAlimento(int id);
    public Alimento verAlimento(int id);
    public ArrayList<Alimento> verAlimentos();

    public void actualizarAlimentos (Alimento alimento);


}
