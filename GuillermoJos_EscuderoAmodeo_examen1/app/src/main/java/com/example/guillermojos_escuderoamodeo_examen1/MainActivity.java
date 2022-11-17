package com.example.guillermojos_escuderoamodeo_examen1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guillermojos_escuderoamodeo_examen1.entidades.DaoExamen;
import com.example.guillermojos_escuderoamodeo_examen1.entidades.examen;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    DaoExamen daoExamen;
    examen examen = new examen();
    String velocidad="m/s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Menu examen");
        toolbar.setSubtitle("menu");
        setSupportActionBar(toolbar);
        daoExamen=new DaoExamen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        // MenuInflater inflater=getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;

    }

    //segunda actividad
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Primer parámetro determina quien envió el primer intent
        //El segundo parámetro identifica el regreso
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == 7) {
            String mensaje = data.getExtras().getString("valor");
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    //Manejo de eventos de toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.ajustes:
                ajustes();
                break;
            case R.id.borrar:
                borrar();
                break;
            case R.id.calcular:
                calcular();
                break;
        }
        return true;
    }

    private void ajustes() {
        alerta().show();
    }

    private void borrar() {
        TextView n1 = ((TextView) findViewById(R.id.editTextNumber));
        n1.setText("");
        TextView n2 = ((TextView) findViewById(R.id.editTextNumber2));
        n2.setText("");

        TextView distancia = ((TextView) findViewById(R.id.editTextTextPersonName));
        distancia.setText("");
        TextView tiempo = ((TextView) findViewById(R.id.editTextTextPersonName2));
        tiempo.setText("");
    }

    private void calcular() {

        Intent intent = new Intent(this,CalcularActivity.class);
        intent.putExtra("examen", (Serializable) this.examen);
        startActivityForResult(intent,1234);

        startActivity(intent);
    }

    //Alertas
    private AlertDialog alerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        //Inflater transforma xml en java
        View view=inflater.inflate(R.layout.activity_ajustes,null);

        builder
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    RadioGroup radioGroup= (RadioGroup) (view.findViewById(R.id.velocidad));

                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);

                    this.velocidad = (String) radioButton.getText();
                    //Editable texto=editText.getText();
                    //p.setDescripcion(texto.toString());
                    Toast.makeText(this,"Ajustes guardados", Toast.LENGTH_SHORT).show();
                    //System.out.println("Test: " + this.velocidad);

                })
                .setNegativeButton("Cancelar",(dialog,id)->{

                    dialog.cancel();
                });


        AlertDialog dialogo= builder.create();

        return dialogo;

    }


}