package com.example.recuexamenmapas_guillermojoseescuderoamodeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.posicionLogLat:
                //posicionLogCat();
                break;
            case R.id.borrarPosiciones:
                //borrarPosiciones();
                break;
            case R.id.ubicacionActual:
                //ubicacionActual();
                break;
            case R.id.posicionDistRumbo:
                //posicionDistRumbo();
                break;

        }
        return true;
    }
}