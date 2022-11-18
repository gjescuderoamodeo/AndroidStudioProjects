package com.example.pruebatoolbar_1.ui;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pruebatoolbar_1.R;
import com.example.pruebatoolbar_1.db.entidades.DaoPosicionImp;
import com.example.pruebatoolbar_1.db.entidades.Posicion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DaoPosicionImp daoPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_titulo);
        toolbar.setSubtitle(getString(R.string.ejemplo));
        setSupportActionBar(toolbar);
        daoPos=new DaoPosicionImp();
     }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu){

       // MenuInflater inflater=getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;

    }


    //Manejo de eventos de toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add_posiciones:
                añadirPosiciones();
                break;
            case R.id.ver_posiciones:
                verPosiciones();
                break;
            case R.id.editar_posiciones:
                verPosiciones();
                break;
            case R.id.eliminar_posiciones:
                eliminarPosiciones();
                break;

        }
        return true;
    }

    private void verPosiciones() {


        ArrayList<Posicion> posiciones =daoPos.verPosiciones();

        for(Posicion p:posiciones){
            Log.d("posiciones",p.getDescripcion());
        }


    }

    private void añadirPosiciones() {

        Posicion p1=new Posicion ();
        alerta(p1).show();
        daoPos.addPosicion(p1);

    }

    private void editarPosiciones() {

        Posicion p1=new Posicion ();
        alerta(p1).show();
        daoPos.addPosicion(p1);

    }

    private void eliminarPosiciones() {

        Posicion p1=new Posicion ();
        alerta2().show();
        //daoPos.deletePosicion(p1);

    }

    private AlertDialog alerta(Posicion p){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        //Inflater transforma xml en java
        View view=inflater.inflate(R.layout.activity_formulario_add_posicion,null);

        builder
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editText=(EditText)(view.findViewById(R.id.descripcion));
                    Editable texto=editText.getText();
                    p.setDescripcion(texto.toString());
                    Toast.makeText(this,"Posición guardada", Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Cancelar",(dialog,id)->{

                    dialog.cancel();
                });


        AlertDialog dialogo= builder.create();

        return dialogo;

    }

    //eliminar alert
    private AlertDialog alerta2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_formulario_remove_posicion,null);

        builder
                .setView(view)
                .setTitle("Eliminar posición")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editText=(EditText)(view.findViewById(R.id.descripcion));
                    Editable texto=editText.getText();

                   //ArrayList<Posicion> posiciones = daoPos.verPosiciones();

                    //for(int i=0;i<posiciones.size();i++){
                    //    if(p.equals(posiciones.get(i).getDescripcion())){
                    //        posiciones.remove(i);
                    //    }
                    //}
                    //p.setDescripcion(texto.toString());
                    //p.setDescripcion(texto.toString());
                    daoPos.deletePosicion(String.valueOf(texto));
                    Toast.makeText(this,"Posición borrada", Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Cancelar",(dialog,id)->{

                    dialog.cancel();
                });


        AlertDialog dialogo= builder.create();

        return dialogo;

    }

    //editar alert
    private AlertDialog alerta3(String descripcion,Posicion p){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_formulario_add_posicion,null);

        builder
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editText=(EditText)(view.findViewById(R.id.descripcion));
                    Editable texto=editText.getText();
                    p.setDescripcion(texto.toString());
                    Toast.makeText(this,"Posición guardada", Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Cancelar",(dialog,id)->{

                    dialog.cancel();
                });


        AlertDialog dialogo= builder.create();

        return dialogo;

    }

}
