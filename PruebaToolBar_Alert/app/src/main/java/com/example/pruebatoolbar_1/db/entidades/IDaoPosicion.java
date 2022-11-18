package com.example.pruebatoolbar_1.db.entidades;

import java.util.ArrayList;

public interface IDaoPosicion {

    public void addPosicion(Posicion p);
    public void deletePosicion(String detalles);
    public void editPosicion(String detalles, Posicion p2);
    public ArrayList<Posicion> verPosiciones();

}
