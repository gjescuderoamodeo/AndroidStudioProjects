package com.example.tabbedtest.InterfacesDAO;

import com.example.tabbedtest.modelo.Coordenada;

import java.util.ArrayList;

public interface IDaoCoordenada {

    public void crearCoordenada(Coordenada coor);
    public void eliminarCoordenada(String nombre);
    public Coordenada verCoordenada(String nombre);
    public ArrayList<Coordenada> verCoordenada();
    public void actualizarCoordenada (Coordenada coor);


}
