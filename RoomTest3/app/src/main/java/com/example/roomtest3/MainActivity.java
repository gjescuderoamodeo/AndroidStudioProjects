package com.example.roomtest3;

import android.os.Bundle;

//import com.example.roomtest3.databinding.ActivityMainBinding;
import com.example.roomtest3.entidades.Lugar;
import com.example.roomtest3.entidades.Ruta;
import com.example.roomtest3.room.ItinerarioBD;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import java.util.List;

//import com.example.roomtest3.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //test de Room
        testRoom();

    }

    public void testRoom(){
        /*CoordenadaBD.getCoordenadaBD(getApplicationContext()).daoCoordenada().nukeTable();

        Coordenada coordenada=new Coordenada(1,2,"pepe");
        Coordenada coordenada2=new Coordenada(1,2,"test");
        //no funciona si está en el hilo principal a no ser que lo especifiquemos
        CoordenadaBD.getCoordenadaBD(getApplicationContext()).daoCoordenada().crearCoordenada(coordenada);
        CoordenadaBD.getCoordenadaBD(getApplicationContext()).daoCoordenada().crearCoordenada(coordenada2);
        List<Coordenada> coordenadas=
                CoordenadaBD.getCoordenadaBD(getApplicationContext()).daoCoordenada().verCoordenada();
        Log.d("CoordenadaAPP","hola mundo");
        for (Coordenada al:coordenadas) {
            Log.d("CoordenadaAPP", al.getNombre() + " " + al.getId());
        }*/

        ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().nukeTable();
        ItinerarioBD.getIinerarioBD(getApplicationContext()).daoRuta().nukeTable();

        Lugar lugar1 = new Lugar(23,20,"Sevilla");
        Lugar lugar2 = new Lugar(23,20,"Cordoba");

        ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().crearLugar(lugar1);
        ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().crearLugar(lugar2);

        Lugar id1 = ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().verLugar("Sevilla");
        Lugar id2 = ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().verLugar("Cordoba");

        Ruta ruta1 = new Ruta(id1.getId(), id2.getId(), "Test");

        ItinerarioBD.getIinerarioBD(getApplicationContext()).daoRuta().crearRuta(ruta1);

        List<Lugar> lugares=
               ItinerarioBD.getIinerarioBD(getApplicationContext()).daoLugar().verLugar();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Lugar al:lugares) {
           Log.d("RutasAPP", al.nombre + " " + "");
        }


        List<Ruta> rutas=
               ItinerarioBD.getIinerarioBD(getApplicationContext()).daoRuta().verRuta();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Ruta al:rutas) {
            Log.d("RutasAPP", al.getNombreValue() + " " + al.destinoId + " " + al.origenId);
        }




    }

    public void cosas(View view) {
        testRoom();
    }
}