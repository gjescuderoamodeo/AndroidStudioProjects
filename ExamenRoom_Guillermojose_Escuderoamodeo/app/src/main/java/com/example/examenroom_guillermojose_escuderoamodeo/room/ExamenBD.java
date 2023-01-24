package com.example.examenroom_guillermojose_escuderoamodeo.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenroom_guillermojose_escuderoamodeo.DAO.AlumnoDao;
import com.example.examenroom_guillermojose_escuderoamodeo.DAO.AsignaturaAlumnoDao;
import com.example.examenroom_guillermojose_escuderoamodeo.DAO.AsignaturaDao;
import com.example.examenroom_guillermojose_escuderoamodeo.DAO.GrupoDao;
import com.example.examenroom_guillermojose_escuderoamodeo.DAO.ProfesorDao;
import com.example.examenroom_guillermojose_escuderoamodeo.constantes.Constantes;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Alumno;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Asignatura;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.AsignaturaAlumno;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Grupo;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Profesor;

//@Database(entities={Alumno.class, Grupo.class, Asignatura.class, AsignaturaAlumno.class, Profesor.class},version=1)
public abstract class ExamenBD extends RoomDatabase {

        public static ExamenBD INSTANCE;
        public abstract AlumnoDao daoAlumno();
        public abstract AsignaturaAlumnoDao daoAsignaturaAlumno();
        public abstract AsignaturaDao daoAsignatura();
        public abstract GrupoDao daoGrupo();
        public abstract ProfesorDao daoProfesor();
        public static ExamenBD getIinerarioBD(Context context){
        if(INSTANCE==null){
            //Usar allowMainThreadQueries() solo para pruebas
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ExamenBD.class,
                    Constantes.NOMBRE_DB).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public void destroyInstance(){
        INSTANCE=null;
    }
    }



