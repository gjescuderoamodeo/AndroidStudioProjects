package com.example.roomtest3.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomtest3.DAO.InterfaceDaoCoordenada;
import com.example.roomtest3.DAO.InterfaceDaoLugar;
import com.example.roomtest3.DAO.InterfaceDaoRuta;
import com.example.roomtest3.constantes.Constantes;
import com.example.roomtest3.entidades.Coordenada;
import com.example.roomtest3.entidades.Lugar;
import com.example.roomtest3.entidades.Ruta;

@Database(entities={Lugar.class, Ruta.class}, version=2)
public abstract class ItinerarioBD extends RoomDatabase {

    public static ItinerarioBD INSTANCE;
    public abstract InterfaceDaoLugar daoLugar();
    public abstract InterfaceDaoRuta daoRuta();

    public static synchronized ItinerarioBD getIinerarioBD(Context context){
        if(INSTANCE==null){
            //Usar allowMainThreadQueries() solo para pruebas
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ItinerarioBD.class,
                            Constantes.NOMBRE_DB2)
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public void destroyInstance(){
        INSTANCE=null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Aquí se definen los cambios que deseas realizar en la base de datos
        }
    };
}








/*@Database(entities={Lugar.class, Ruta.class},version=1)
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
    }*/



