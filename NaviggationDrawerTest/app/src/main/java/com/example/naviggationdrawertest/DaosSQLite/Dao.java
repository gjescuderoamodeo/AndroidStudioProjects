package com.example.naviggationdrawertest.DaosSQLite;

import android.database.sqlite.SQLiteOpenHelper;

import com.example.naviggationdrawertest.modelo.Alimento;

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
