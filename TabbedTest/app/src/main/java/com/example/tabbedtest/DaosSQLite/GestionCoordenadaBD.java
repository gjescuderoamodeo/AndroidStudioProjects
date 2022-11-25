package com.example.tabbedtest.DaosSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestionCoordenadaBD extends SQLiteOpenHelper  {
    String sqlCreate1="CREATE TABLE Coordenada (idDep INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, x INTEGER, y INTEGER)";
    //String sqlCreate2="CREATE TABLE Profesores (idPro INTEGER PRIMARY KEY AUTOINCREMENT,  departamento INTEGER, nombre TEXT,observaciones TEXT,  FOREIGN KEY (departamento) REFERENCES Departamentos (idDep))";
    //Al guardar en profesores hay que ejecutar SQL="PRAGMA foreign_keys=ON"

   public GestionCoordenadaBD(Context context)
   {

       super(context,"BDDepartamentos2",null,1);


   }


    //Solo llama a este método si la base de datos no existe
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate1);
        //db.execSQL(sqlCreate2);
        db.execSQL("PRAGMA foreign_keys = ON;");

        int x=1;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        int x=1;
    }

    public void borrarTablas(){
        String sql1="DROP TABLE Coordenada";
        //String sql2="DROP TABLE Profesores";
        SQLiteDatabase conexion=getWritableDatabase();
        conexion.execSQL(sql1);
        //conexion.execSQL(sql2);
        conexion.execSQL("PRAGMA foreign_keys = ON;");
        conexion.close();
   }
}
