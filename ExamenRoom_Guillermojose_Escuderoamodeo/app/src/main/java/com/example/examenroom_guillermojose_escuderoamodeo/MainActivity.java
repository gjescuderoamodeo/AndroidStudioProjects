package com.example.examenroom_guillermojose_escuderoamodeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Alumno;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Asignatura;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.AsignaturaAlumno;
import com.example.examenroom_guillermojose_escuderoamodeo.entidades.Grupo;
import com.example.examenroom_guillermojose_escuderoamodeo.room.ExamenBD;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*public void ExamenRoom(){

        //Querrys para limpiar la BD
        ExamenBD.getExamenBD(getApplicationContext()).daoAsignatura().nukeTable();
        ExamenBD.getExamenBD(getApplicationContext()).daoGrupo().nukeTable();
        ExamenBD.getExamenBD(getApplicationContext()).daoAlumno().nukeTable();
        ExamenBD.getExamenBD(getApplicationContext()).daoAsignaturaAlumno().nukeTable();
        //ExamenBD.getExamenBD(getApplicationContext()).daoAsignatura().nukeTable();


        //creo alumnos
        Alumno alumno1 = new Alumno("Guillermo","Escudero");
        Alumno alumno2 = new Alumno("Juan Carlos","Bodoque");
        Alumno alumno3 = new Alumno("Perico","gutierrez");

        //creo asignaturas
        //array alumnos
        ArrayList<Alumno> alumnos1 = new ArrayList<>();
        alumnos1.add(alumno1);
        alumnos1.add(alumno2);
        alumnos1.add(alumno3);

        Asignatura asignatura1 = new Asignatura("Matemáticas",alumnos1);

        //creo asignatura alumno
        AsignaturaAlumno asignaturaAlumno1 = new AsignaturaAlumno(1,1);

        //creo grupo
        Grupo grupo1 = new Grupo("1","DAW","33A",alumnos1);

        //añado bd
        ExamenBD.getExamenBD(getApplicationContext()).daoAlumno().insert(alumno1);
        ExamenBD.getExamenBD(getApplicationContext()).daoAlumno().insert(alumno2);

        //ExamenBD.getExamenBD(getApplicationContext()).daoAsignatura().insert(asignatura1);
        //ExamenBD.getExamenBD(getApplicationContext()).daoGrupo().insert(grupo1);
        //ExamenBD.getExamenBD(getApplicationContext()).daoAsignaturaAlumno().insert(asignaturaAlumno1);

        List<Alumno> alumnos=
                ExamenBD.getExamenBD(getApplicationContext()).daoAlumno().getAllAlumnos();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Alumno al:alumnos) {
            Log.d("ExamenRoom", al.id + " nombre: " + al.getNombre());
        }

    }*/
}