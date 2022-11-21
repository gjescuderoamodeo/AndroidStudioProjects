package com.example.proyectoampliado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import modelo.Posicion;

public class MainActivity extends AppCompatActivity {

    ArrayList<Posicion> posiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posiciones = new ArrayList<>();
    }

    public void add(View view){

        TextView x = ((TextView) findViewById(R.id.x));
        TextView y = ((TextView) findViewById(R.id.y));
        TextView descripcion = ((TextView) findViewById(R.id.descripcion));

        Posicion posicion = new Posicion(Integer.parseInt((String.valueOf(x.getText()))),
                Integer.parseInt((String.valueOf(y.getText()))),
                String.valueOf(descripcion.getText()));

        if(x!=null && y!=null){
            this.posiciones.add(posicion);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Faltan datos en el formulario";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void ver(View view){
        for (Posicion item : this.posiciones) {
            System.out.println("x: " + item.getX() + " y: " + item.getY()
                    + " descripción: " + item.getDescripcion());
        }
    }


}