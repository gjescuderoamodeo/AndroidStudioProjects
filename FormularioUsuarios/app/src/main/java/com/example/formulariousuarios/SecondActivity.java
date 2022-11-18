package com.example.formulariousuarios;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.formulariousuarios.databinding.ActivitySecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1lista
        //lista=(ListView)findViewById(R.id.list_view);

        //2 Cargar lista
        Bundle extras = this.getIntent().getExtras();
        List<Object> usuarios = (List<Object>)extras.getSerializable("usuarios");

        //mensaje
        String mensaje=extras.getString("mensaje");
        TextView text1 = (TextView) findViewById(R.id.textView2);

        for(Object us:usuarios){
            text1.setText(mensaje);
        }






    }

}