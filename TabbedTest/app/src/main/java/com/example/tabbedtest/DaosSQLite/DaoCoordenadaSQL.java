package com.example.tabbedtest.DaosSQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tabbedtest.InterfacesDAO.IDaoCoordenada;
import com.example.tabbedtest.modelo.Coordenada;

import java.util.ArrayList;

public class DaoCoordenadaSQL extends Dao implements IDaoCoordenada {

    private GestionCoordenadaBD gdep;
    private SQLiteDatabase conexion;
    public DaoCoordenadaSQL(){
        this.gdep=(GestionCoordenadaBD)Dao.gestorBd;

    }

    @Override
    public void crearCoordenada(Coordenada dep) {

       conexion =gdep.getWritableDatabase();
       //Una forma de insertar parametrizada

       ContentValues registro =new ContentValues();
       registro.put("nombre",dep.getNombre());
       registro.put("x",dep.getX());
       registro.put("y",dep.getY());
       conexion.insert("Coordenada",null,registro);

       conexion.close();


    }
    @Override
    public void eliminarCoordenada(String nombre) {}

    @Override
    public Coordenada verCoordenada(String nombre) {
        Coordenada dep=new Coordenada();
        //String consulta="SELECT * FROM Departamentos WHERE idDep="+id;
        String consulta="SELECT * FROM Coordenada WHERE nombre=?";
        String[] param=new String[1];
        param[0]="1";
        conexion =gdep.getWritableDatabase();
        Cursor c= conexion.rawQuery(consulta,param);
        if (c.moveToFirst()){
                dep.setNombre(c.getString(0));
                dep.setX(c.getInt(1));
                dep.setY(c.getInt(2));
            }
        conexion.close();
        return dep;
    }
    @Override
    public ArrayList<Coordenada> verCoordenada() {
        ArrayList<Coordenada> coordenadas = new ArrayList<>();
        conexion =gdep.getWritableDatabase();
        String consulta = "SELECT * FROM Coordenada";
        Cursor c = conexion.rawQuery(consulta, null);
        if (c.moveToFirst()) {
            do {
                Coordenada dep = new Coordenada();
                dep.setNombre(c.getString(0));
                dep.setX(c.getInt(1));
                dep.setY(c.getInt(2));
                coordenadas.add(dep);
            } while (c.moveToNext());

        }
        conexion.close();
        return coordenadas;
    }
    @Override
    public void actualizarCoordenada(Coordenada dep) {

    }
}
