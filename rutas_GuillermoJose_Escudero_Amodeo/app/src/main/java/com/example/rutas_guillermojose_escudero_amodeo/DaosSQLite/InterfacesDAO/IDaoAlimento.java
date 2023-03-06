package com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.InterfacesDAO;

import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;

import java.util.ArrayList;

public interface IDaoAlimento {

    public void crearAlimento(Alimento dep);
    public void eliminarAlimento(int id);
    public Alimento verAlimento(int id);
    public ArrayList<Alimento> verAlimentos();

    public void actualizarAlimentos (Alimento alimento);


}
