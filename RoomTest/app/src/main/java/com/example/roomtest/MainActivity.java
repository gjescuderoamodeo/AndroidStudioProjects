package com.example.roomtest;

import android.os.Bundle;

import com.example.roomtest.entidades.Coordenada;
import com.example.roomtest.room.CoordenadaBD;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.roomtest.ui.main.SectionsPagerAdapter;
import com.example.roomtest.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
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
        });

        //test de Room
        testRoom();

    }

    public void testRoom(){
        CoordenadaBD.getCoordenadaBD(getApplicationContext()).daoCoordenada().nukeTable();

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
        }
    }

}