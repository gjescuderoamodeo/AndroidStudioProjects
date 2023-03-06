package com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite;

import android.database.sqlite.SQLiteOpenHelper;

import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;

import java.util.ArrayList;

public abstract class Dao {

    static public SQLiteOpenHelper gestorBd;

    public Dao(){}






    static public void setGestorBD(SQLiteOpenHelper gbd){

        gestorBd=gbd;
    }

    public abstract ArrayList<Alimento> verAlimento();

    public abstract void actualizarAlimento(Alimento alimento);
}
