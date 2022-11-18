package com.example.formulariousuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String nombre="";
    String apellidos="";
    String genero="";
    List<String> opcionales = new ArrayList<String>();
    List<Object> usuarios = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //
    public void pulsar(View view){

        Intent intent = new Intent(this,SegundaActividad.class);
        intent.putExtra("mensaje","Hola test");
        intent.putExtra("usuarios", (Serializable) this.usuarios);
        startActivityForResult(intent,1234);

        startActivity(intent);

    }

    public void lanzarActivityConRetorno() {
        Intent intent=new Intent(this, SegundaActividad.class);
        startActivityForResult(intent,1234);
    }

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

    //
    public void add(View view){

        TextView name = ((TextView) findViewById(R.id.name));
        TextView name2 = ((TextView) findViewById(R.id.name2));
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genero);


        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);

        //arrayList donde guardo los checkbox
        List<CheckBox> items = new ArrayList<CheckBox>();
        CheckBox checkeo = (CheckBox) findViewById(R.id.checkBox);
        CheckBox checkeo2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkeo3 = (CheckBox) findViewById(R.id.checkBox3);

        items.add(checkeo); items.add(checkeo2); items.add(checkeo3);

        for (CheckBox item : items){
            if(item.isChecked()){
                this.opcionales.add(item.getText().toString());
            }
            /*else{
                this.opcionales.remove(item.getText().toString());
            }*/

        }

        this.genero = (String) radioButton.getText();
        this.nombre = String.valueOf(name.getText());
        this.apellidos = String.valueOf(name2.getText());


        //si los datos están bien, añado al array usuarios los datos del formulario
        if(this.nombre!="" && this.apellidos!="" && this.genero!=""){

            List<Object> usuariosDatos = new ArrayList<>();
            usuariosDatos.add(this.nombre);
            usuariosDatos.add(this.apellidos);
            usuariosDatos.add(this.genero);
            if(!this.opcionales.isEmpty()){
                usuariosDatos.add(this.opcionales);
            }

            usuarios.add(usuariosDatos);

            //una vez añadido, limpio el formulario
            this.reset(view);


        }else{
            Context context = getApplicationContext();
            CharSequence text = "Faltan datos en el formulario";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void reset(View view){

        TextView name = ((TextView) findViewById(R.id.name));
        TextView name2 = ((TextView) findViewById(R.id.name2));
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genero);

        CheckBox checkeo = (CheckBox) findViewById(R.id.checkBox);
        CheckBox checkeo2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkeo3 = (CheckBox) findViewById(R.id.checkBox3);

        this.opcionales.clear();
        this.genero="";
        this.nombre="";
        this.apellidos="";

        name.setText("");
        name2.setText("");
        checkeo.setChecked(false);
        checkeo2.setChecked(false);
        checkeo3.setChecked(false);
        radioGroup.clearCheck();
    }

    public void ver(View view){
        for (Object item : this.usuarios) {
            System.out.println("ver:" +item);
        }
    }

}