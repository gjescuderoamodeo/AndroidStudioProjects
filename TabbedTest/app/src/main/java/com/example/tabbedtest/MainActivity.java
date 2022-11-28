package com.example.tabbedtest;

import android.os.Bundle;

import com.example.tabbedtest.DaosSQLite.Dao;
import com.example.tabbedtest.DaosSQLite.DaoCoordenadaSQL;
import com.example.tabbedtest.DaosSQLite.GestionCoordenadaBD;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.tabbedtest.ui.main.SectionsPagerAdapter;
import com.example.tabbedtest.databinding.ActivityMainBinding;

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

        Dao.setGestorBD(new GestionCoordenadaBD(this));
        //DaoCoordenadaSQL daocod=new DaoCoordenadaSQL();
    }
}