package com.example.roomtest3.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomtest3.DAO.InterfaceDaoCoordenada;
import com.example.roomtest3.constantes.Constantes;
import com.example.roomtest3.entidades.Coordenada;

@Database(entities={Coordenada.class},version=1)
public abstract class CoordenadaBD extends RoomDatabase {

        public static CoordenadaBD INSTANCE;
        public abstract InterfaceDaoCoordenada daoCoordenada();
        public static CoordenadaBD getCoordenadaBD(Context context){
            if(INSTANCE==null){
            //Usar allowMainThreadQueries() solo para pruebas
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),CoordenadaBD.class,
                        Constantes.NOMBRE_DB).allowMainThreadQueries().build();
            }
            return INSTANCE;
        }
        public void destroyInstance(){
            INSTANCE=null;
        }
    }



