package com.example.examenroom_guillermojose_escuderoamodeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.examenroom_guillermojose_escuderoamodeo.room.ExamenBD;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ExamenRoom(){

        //Querry para limpiar la BD
        ExamenBD.getExamenBD(getApplicationContext()).daoAsignatura().nukeTable();



    }
}