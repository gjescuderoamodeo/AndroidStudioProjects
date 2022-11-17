package com.example.navigattionfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    boolean f1=false;
    boolean f2=false;
    boolean f3=false;
    static int numero=0;
    Fragment mn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargar un fragment en un Activity mediante código
        mn=new Fragment1();
        loadFragment(mn,"f2");

        Button butt = findViewById(R.id.button2);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment f = null;
                if (f1){
                    replaceFragment(mn,"noche");
                }
                else {
                    mn= (Fragment2)getSupportFragmentManager().findFragmentByTag("f2");
                    loadFragment(new Fragment1(),"f1");
                }
                f1=!f2;


            }
        });
    }

    private void loadFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, f,tag).addToBackStack(null)
                .commit();
    }

    private void replaceFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, f,tag)
                .commit();
    }

    private void removeFragment(Fragment f){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(f)
                .commit();
    }










}