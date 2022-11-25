package com.example.tabbedtest.DaosSQLite;

import android.database.sqlite.SQLiteOpenHelper;

public class Dao {

    static public SQLiteOpenHelper gestorBd;

    public Dao(){}






    static public void setGestorBD(SQLiteOpenHelper gbd){

        gestorBd=gbd;
    }
}
