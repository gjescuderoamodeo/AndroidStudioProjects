package com.example.formulariousuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TerceraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        devolverResults();
    }

    public void devolverResults() {
        Intent intent=new Intent();
        intent.putExtra("valor","ya estoy de vuelta");
        setResult(7,intent);
        finish();
    }





}