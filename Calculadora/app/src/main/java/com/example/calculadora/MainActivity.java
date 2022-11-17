package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double numero1=0;
    double numero2=0;
    double resultado = 0;

    //ArrayList<Integer> numeros = new ArrayList<Integer>();

    String operacion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void numero(View view) {

        System.out.println(view);

        //TextView texto = ((TextView) findViewById(R.id.textView));
        //String añadir = String.valueOf(texto)+"1";

        //texto.setText(añadir);
        //Toast.makeText(context: this, text:"esto es un mensaje", Toast.LEGHT_LONG).show();
        //System.out.println(texto);
    }
    public void n0(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        if(etiqueta.getText()!=""){
            String añadir = etiqueta.getText()+"0";
            etiqueta.setText(añadir);
        }

    }

    public void n1(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"1";
        etiqueta.setText(añadir);
    }

    public void n2(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"2";
        etiqueta.setText(añadir);
    }

    public void n3(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"3";
        etiqueta.setText(añadir);
    }
    public void n4(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"4";
        etiqueta.setText(añadir);
    }
    public void n5(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"5";
        etiqueta.setText(añadir);
    }
    public void n6(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"6";
        etiqueta.setText(añadir);
    }
    public void n7(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"7";
        etiqueta.setText(añadir);
    }
    public void n8(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"8";
        etiqueta.setText(añadir);
    }
    public void n9(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        String añadir = etiqueta.getText()+"9";
        etiqueta.setText(añadir);
    }
    public void c(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));
        this.numero1=0;
        this.numero2=0;
        this.operacion="";
        this.resultado=0;
        etiqueta.setText("");
    }

    public void sumar(View view) {
        //al pulsar el botón sumar, guarda el numero actual y guarda el valor de la operación,
        //sustituyendo el anterior

        //en caso de que resultado sea 0
        if(resultado==0) {
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            //en caso de que no le de + y no hay nada
            if(!String.valueOf(etiqueta.getText()).equals("")){
                //en el caso que numero 1 este vacio y 2 tbm
                if(this.numero1==0 && this.numero2==0){
                    this.operacion = "+";
                    this.numero1 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    //System.out.println(Double.parseDouble(String.valueOf(etiqueta.getText())));
                    etiqueta.setText("");
                    return;
                }
                if(this.numero1!=0 && this.numero2==0){
                    this.operacion = "+";
                    this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    this.resultado=this.numero1+this.numero2;
                    this.numero2=0;
                    etiqueta.setText(String.valueOf(this.resultado));
                    return;
                }
            }
        }else{
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            if(!String.valueOf(etiqueta.getText()).equals("")){
                this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                this.resultado+=this.numero2;
                etiqueta.setText(String.valueOf(this.resultado));
                return;
            }
        }

    }

    public void multiplicar(View view) {
        //al pulsar el botón sumar, guarda el numero actual y guarda el valor de la operación,
        //sustituyendo el anterior

        //en caso de que resultado sea 0
        if(resultado==0) {
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            //en caso de que no le de + y no hay nada
            if(!String.valueOf(etiqueta.getText()).equals("")){
                //en el caso que numero 1 este vacio y 2 tbm
                if(this.numero1==0 && this.numero2==0){
                    this.operacion = "*";
                    this.numero1 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    //System.out.println(Double.parseDouble(String.valueOf(etiqueta.getText())));
                    etiqueta.setText("");
                    return;
                }
                if(this.numero1!=0 && this.numero2==0){
                    this.operacion = "*";
                    this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    this.resultado=this.numero1*this.numero2;
                    this.numero2=0;
                    etiqueta.setText(String.valueOf(this.resultado));
                    return;
                }
            }
        }else{
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            if(!String.valueOf(etiqueta.getText()).equals("")){
                this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                this.resultado*=this.numero2;
                etiqueta.setText(String.valueOf(this.resultado));
                return;
            }
        }

    }

    public void restar(View view) {
        //al pulsar el botón sumar, guarda el numero actual y guarda el valor de la operación,
        //sustituyendo el anterior

        //en caso de que resultado sea 0
        if(resultado==0) {
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            //en caso de que no le de + y no hay nada
            if(!String.valueOf(etiqueta.getText()).equals("")){
                //en el caso que numero 1 este vacio y 2 tbm
                if(this.numero1==0 && this.numero2==0){
                    this.operacion = "-";
                    this.numero1 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    //System.out.println(Double.parseDouble(String.valueOf(etiqueta.getText())));
                    etiqueta.setText("");
                    return;
                }
                if(this.numero1!=0 && this.numero2==0){
                    this.operacion = "-";
                    this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    this.resultado=this.numero1-this.numero2;
                    this.numero2=0;
                    etiqueta.setText(String.valueOf(this.resultado));
                    return;
                }
            }
        }else{
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            if(!String.valueOf(etiqueta.getText()).equals("")){
                this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                this.resultado-=this.numero2;
                etiqueta.setText(String.valueOf(this.resultado));
                return;
            }
        }

    }

    public void dividir(View view) {
        //al pulsar el botón sumar, guarda el numero actual y guarda el valor de la operación,
        //sustituyendo el anterior

        //en caso de que resultado sea 0
        if(resultado==0) {
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            //en caso de que no le de + y no hay nada
            if(!String.valueOf(etiqueta.getText()).equals("")){
                //en el caso que numero 1 este vacio y 2 tbm
                if(this.numero1==0 && this.numero2==0){
                    this.operacion = "/";
                    this.numero1 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    //System.out.println(Double.parseDouble(String.valueOf(etiqueta.getText())));
                    etiqueta.setText("");
                    return;
                }
                if(this.numero1!=0 && this.numero2==0){
                    this.operacion = "/";
                    this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                    this.resultado=this.numero1-this.numero2;
                    this.numero2=0;
                    etiqueta.setText(String.valueOf(this.resultado));
                    return;
                }
            }
        }else{
            TextView etiqueta = ((TextView) findViewById(R.id.textView));
            if(!String.valueOf(etiqueta.getText()).equals("")){
                this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                this.resultado/=this.numero2;
                etiqueta.setText(String.valueOf(this.resultado));
                return;
            }
        }

    }

    public void resultado(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));

        if(!String.valueOf(etiqueta.getText()).equals("")){
            switch(this.operacion){
                case "+":
                    if(this.numero1!=0 && this.numero2==0){
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado=this.numero1+this.numero2;
                        this.numero2=0;
                        etiqueta.setText(String.valueOf(this.resultado));
                        return;
                    }else{
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado+=this.numero2;
                        etiqueta.setText(String.valueOf(this.resultado));
                    }
                    break;
                case "*":
                    if(this.numero1!=0 && this.numero2==0){
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado=this.numero1*this.numero2;
                        this.numero2=0;
                        etiqueta.setText(String.valueOf(this.resultado));
                        return;
                    }else{
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado*=this.numero2;
                        etiqueta.setText(String.valueOf(this.resultado));
                    }
                    break;
                case "-":
                    if(this.numero1!=0 && this.numero2==0){
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado=this.numero1-this.numero2;
                        this.numero2=0;
                        etiqueta.setText(String.valueOf(this.resultado));
                        return;
                    }else{
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado-=this.numero2;
                        etiqueta.setText(String.valueOf(this.resultado));
                    }
                    break;
                case "/":
                    if(this.numero1!=0 && this.numero2==0){
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado=this.numero1/this.numero2;
                        this.numero2=0;
                        etiqueta.setText(String.valueOf(this.resultado));
                        return;
                    }else{
                        this.numero2 = Double.parseDouble(String.valueOf(etiqueta.getText()));
                        this.resultado/=this.numero2;
                        etiqueta.setText(String.valueOf(this.resultado));
                    }
                    break;
                default:
                    etiqueta.setText("0");
            }
        }else{
            etiqueta.setText("0");
        }


        }

    public void retroceso(View view) {
        TextView etiqueta = ((TextView) findViewById(R.id.textView));

        etiqueta.setText("");
    }

    }









