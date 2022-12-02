package com.example.tabbedtest;

import android.os.Bundle;

import com.example.tabbedtest.DaosSQLite.Dao;
import com.example.tabbedtest.DaosSQLite.DaoCoordenadaSQL;
import com.example.tabbedtest.DaosSQLite.GestionCoordenadaBD;
import com.example.tabbedtest.modelo.Coordenada;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabbedtest.ui.main.SectionsPagerAdapter;
import com.example.tabbedtest.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;
    DaoCoordenadaSQL daocod;

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
        try{
            Dao.setGestorBD(new GestionCoordenadaBD(this));
            daocod=new DaoCoordenadaSQL();
            añadirCoordenada();
        }catch(Exception e){
            int x=1;
        }
        //DaoCoordenadaSQL daocod=new DaoCoordenadaSQL();



        //Test de SQLite





    }

    public void añadirCoordenada(){
        int x = 2;
        int y = 3;
        String nombre = "Test";

        //Enviar datos al activity main

        Coordenada coord=new Coordenada(x,y,nombre);


        daocod.crearCoordenada(coord);
        Toast.makeText(this,"Ajustes guardados", Toast.LENGTH_SHORT).show();
        ArrayList<Coordenada> coordenadas = this.daocod.verCoordenada();
        System.out.println(coordenadas);

    }
}