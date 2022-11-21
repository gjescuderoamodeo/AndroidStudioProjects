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

    String fr = "f1";

    static int numero=0;
    Fragment mn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargar un fragment en un Activity mediante código
        mn=new Fragment1();
        loadFragment(mn,"f1");

        Button butt = findViewById(R.id.button2);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment f = null;
                switch (fr){
                    case "f1":
                        mn= (Fragment1)getSupportFragmentManager().findFragmentByTag("f1");
                        loadFragment(new Fragment2(),"f2");
                        removeFragment(mn);
                        fr="f2";
                        break;
                    case "f2":
                        break;
                    case "f3":
                        mn= (Fragment3)getSupportFragmentManager().findFragmentByTag("f3");
                        loadFragment(new Fragment2(),"f2");
                        removeFragment(mn);
                        fr="f2";
                        break;
                }

                /*Fragment f = null;
        if (f2){
            replaceFragment(mn,"f1");
        }
        else {
            mn= (Fragment1)getSupportFragmentManager().findFragmentByTag("f1");
            loadFragment(new Fragment2(),"f2");
            removeFragment(mn);
        }
        f1=!f1;*/


            }
        });


        Button butt3 = findViewById(R.id.button3);
        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = null;
                switch (fr){
                    case "f1":
                        mn= (Fragment1)getSupportFragmentManager().findFragmentByTag("f1");
                        loadFragment(new Fragment3(),"f3");
                        removeFragment(mn);
                        fr="f3";
                        break;
                    case "f2":
                        mn= (Fragment2)getSupportFragmentManager().findFragmentByTag("f2");
                        loadFragment(new Fragment3(),"f3");
                        removeFragment(mn);
                        fr="f3";
                        break;
                    case "f3":
                        break;
                }

/*                Fragment f = null;
                if (f2){
                    replaceFragment(mn,"f1");
                }
                else {
                    mn= (Fragment1)getSupportFragmentManager().findFragmentByTag("f1");
                    loadFragment(new Fragment3(),"f3");
                    removeFragment(mn);
                }
                f1=!f1;*/


            }
        });

        Button butt1 = findViewById(R.id.button);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = null;
                switch (fr){
                    case "f1":
                        break;
                    case "f2":
                        mn= (Fragment2)getSupportFragmentManager().findFragmentByTag("f2");
                        loadFragment(new Fragment1(),"f1");
                        removeFragment(mn);
                        fr="f1";
                        break;
                    case "f3":
                        mn= (Fragment3)getSupportFragmentManager().findFragmentByTag("f3");
                        loadFragment(new Fragment1(),"f1");
                        removeFragment(mn);
                        fr="f1";
                        break;
                }
            }
        });
    }

    public void pulsar(View view){
        Fragment f = null;
        if (f2){
            replaceFragment(mn,"f1");
        }
        else {
            mn= (Fragment1)getSupportFragmentManager().findFragmentByTag("f1");
            loadFragment(new Fragment2(),"f2");
        }
        f1=!f1;
    }

    private void loadFragment(Fragment f,String tag){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, f,tag)
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