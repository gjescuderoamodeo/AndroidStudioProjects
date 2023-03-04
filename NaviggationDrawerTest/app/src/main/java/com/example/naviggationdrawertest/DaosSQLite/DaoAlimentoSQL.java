package com.example.naviggationdrawertest.DaosSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.naviggationdrawertest.DaosSQLite.InterfacesDAO.IDaoAlimento;
import com.example.naviggationdrawertest.modelo.Alimento;

import java.util.ArrayList;

public class DaoAlimentoSQL extends Dao implements IDaoAlimento {

    private GestionAlimentossBD gdep;
    private SQLiteDatabase conexion;

    public DaoAlimentoSQL(Context context){
        this.gdep = new GestionAlimentossBD(context);
    }

    @Override
    public void crearAlimento(Alimento alimento) {
        conexion = gdep.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", alimento.getNombre());
        registro.put("kcal", alimento.getKcal());
        conexion.insert("Alimentos", null, registro);
        conexion.close();
    }

    @Override
    public void eliminarAlimento(int id) {
        conexion = gdep.getWritableDatabase();
        conexion.delete("Alimentos", "_id=" + id, null);
        conexion.close();
    }

    @Override
    public Alimento verAlimento(int id) {
        Alimento alimento = null;
        conexion = gdep.getReadableDatabase();
        Cursor cursor = conexion.rawQuery("SELECT * FROM Alimentos WHERE _id=" + id, null);
        if(cursor.moveToFirst()){
            alimento = new Alimento();
            alimento.setNombre(cursor.getString(0));
            alimento.setKcal(cursor.getInt(1));
        }
        cursor.close();
        conexion.close();
        return alimento;
    }

    @Override
    public ArrayList<Alimento> verAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        conexion = gdep.getReadableDatabase();
        Cursor cursor = conexion.rawQuery("SELECT * FROM Alimentos", null);
        if(cursor.moveToFirst()){
            do{
                Alimento alimento = new Alimento();
                alimento.setNombre(cursor.getString(0));
                alimento.setKcal(cursor.getInt(1));
                alimentos.add(alimento);
            }while(cursor.moveToNext());
        }
        cursor.close();
        conexion.close();
        return alimentos;
    }

    @Override
    public void actualizarAlimentos(Alimento alimento) {

    }

    @Override
    public ArrayList<Alimento> verAlimento() {
        return null;
    }

    @Override
    public void actualizarAlimento(Alimento alimento) {
        conexion = gdep.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", alimento.getNombre());
        registro.put("kcal", alimento.getKcal());
        //conexion.update("Alimentos", registro, "_id=" + alimento.getId(), null);
        conexion.close();
    }
}
