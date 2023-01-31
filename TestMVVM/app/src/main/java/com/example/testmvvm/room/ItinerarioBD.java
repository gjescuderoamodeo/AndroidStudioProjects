package com.example.testmvvm.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testmvvm.DAO.InterfaceDaoLugar;
import com.example.testmvvm.DAO.InterfaceDaoRuta;
import com.example.testmvvm.constantes.Constantes;
import com.example.testmvvm.entidades.Lugar;
import com.example.testmvvm.entidades.Ruta;

@Database(entities={Lugar.class, Ruta.class},version=1)
public abstract class ItinerarioBD extends RoomDatabase {

        public static ItinerarioBD INSTANCE;
        public abstract InterfaceDaoLugar daoLugar();
        public abstract InterfaceDaoRuta daoRuta();
        public static ItinerarioBD getIinerarioBD(Context context){
            if(INSTANCE==null){
            //Usar allowMainThreadQueries() solo para pruebas
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ItinerarioBD.class,
                        Constantes.NOMBRE_DB2).allowMainThreadQueries().build();
            }
            return INSTANCE;
        }
        public void destroyInstance(){
            INSTANCE=null;
        }
    }



