package com.example.guillermojos_escuderoamodeo_examen1.entidades;

import java.util.ArrayList;

public class DaoExamen implements IDaoExamen {

    //para patrones singleTone
    static DaoExamen INSTANCIA=null;

    public static DaoExamen getInstance(){
        if(INSTANCIA==null) INSTANCIA = new DaoExamen();
        return INSTANCIA;
    }

    @Override
    public void addExamen(examen e) {
        ModeloDB.addExamen(e);
    }

    @Override
    public void deleteExamen(examen e) {

    }
}
