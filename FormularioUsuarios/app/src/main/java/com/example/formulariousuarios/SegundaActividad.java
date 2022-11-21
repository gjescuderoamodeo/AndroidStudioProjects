package com.example.formulariousuarios;

import static com.example.formulariousuarios.R.id.textView2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SegundaActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);


        //1lista
        //lista=(ListView)findViewById(R.id.list_view);

        //2 Cargar lista
        Bundle extras = this.getIntent().getExtras();
        List<Object> usuarios = (List<Object>)extras.getSerializable("usuarios");

        //mensaje
        String mensaje=extras.getString("mensaje");
        TextView text1 = ((TextView) findViewById(textView2));

        for(Object us:usuarios){
            text1.setText(text1.getText() + "\n" + us);
        }
    }

    public void devolverResults() {
        Intent intent=new Intent();
        intent.putExtra("valor","ya estoy de vuelta");
        setResult(7,intent);
        finish();
    }


}