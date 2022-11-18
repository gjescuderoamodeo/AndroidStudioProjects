package com.example.pruebatoolbar_1.db.entidades;

import java.util.ArrayList;

public class DaoPosicionImp implements IDaoPosicion{


    @Override
    public void addPosicion(Posicion p) {

        ModeloDB.addPosicion(p);
    }

    @Override
    public void deletePosicion(String detalles) {
        ModeloDB.deletePosicion(detalles);
    }

    @Override
    public void editPosicion(String detalles,Posicion p2) {
        ModeloDB.deletePosicion(detalles);
        ModeloDB.addPosicion(p2);
    }

    @Override
    public ArrayList<Posicion> verPosiciones() {
        return ModeloDB.getPosiciones();
    }
}
