package com.example.primerejercicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText n;
    EditText n2;
    EditText n3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         n = ((EditText) findViewById(R.id.editTextNumber));
         n2 = ((EditText) findViewById(R.id.editTextNumber2));
         n3 = ((EditText) findViewById(R.id.editTextNumber3));
    }

    public void media(View view) {

        TextView etiqueta = ((TextView)findViewById(R.id.textView));

        if(n.getText()!=null){
            int numero1 = Integer.parseInt(n.getText().toString());
            int numero2 = Integer.parseInt(n2.getText().toString());
            int numero3 = Integer.parseInt(n3.getText().toString());
            if(numero1!=0 && numero2!=0
                    && numero3!=0){
                int resultado = (numero1+numero2+numero3)/3;
                etiqueta.setText(resultado+" ");

                //TextView texto = ((TextView) findViewById(R.id.textView));
            }

        }




        //Button boton=((Button)findViewById(R.id.media));


        //etiqueta.setText("Hola");
        //boton.setText("Botón pulsado");



    }

    public void reset(View view) {

        TextView etiqueta = ((TextView)findViewById(R.id.textView));
        TextView t1 = ((TextView)findViewById(R.id.editTextNumber));
        TextView t2 = ((TextView)findViewById(R.id.editTextNumber2));
        TextView t3 = ((TextView)findViewById(R.id.editTextNumber3));

        Button boton=((Button)findViewById(R.id.reset));

            etiqueta.setText("Resultado");
            t1.setText(" ");
            t2.setText(" ");
            t3.setText(" ");

    }


}