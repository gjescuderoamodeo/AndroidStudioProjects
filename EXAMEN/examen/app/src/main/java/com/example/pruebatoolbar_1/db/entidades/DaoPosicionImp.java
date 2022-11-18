package com.example.pruebatoolbar_1.db.entidades;

import java.util.ArrayList;

public class DaoPosicionImp implements IDaoPosicion{

    //para patrones singleTone
    static DaoPosicionImp INSTANCIA=null;

    public static  DaoPosicionImp getInstance(){
        if(INSTANCIA==null) INSTANCIA = new DaoPosicionImp();
        return INSTANCIA;
    }
    //

    @Override
    public void addPosicion(Posicion p) {

        ModeloDB.addPosicion(p);
    }

    @Override
    public void deletePosicion(String p) {
        ModeloDB.deletePosicion(p);
    }

    @Override
    public void editPosicion(String p,Posicion p2) {
        ModeloDB.deletePosicion(p);
        ModeloDB.addPosicion(p2);
    }

    @Override
    public ArrayList<Posicion> verPosiciones() {
        return ModeloDB.getPosiciones();
    }
}
