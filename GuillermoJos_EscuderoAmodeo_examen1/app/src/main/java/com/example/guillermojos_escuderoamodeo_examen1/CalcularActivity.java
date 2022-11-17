package com.example.guillermojos_escuderoamodeo_examen1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import static com.example.guillermojos_escuderoamodeo_examen1.R.id.textView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guillermojos_escuderoamodeo_examen1.entidades.examen;

import java.util.List;

public class CalcularActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);

        //2 Cargar examen
        Bundle extras = this.getIntent().getExtras();
        examen examen = (com.example.guillermojos_escuderoamodeo_examen1.entidades.examen) extras.getSerializable("examen");

        //texto del layaout
        TextView text1 = ((TextView) findViewById(textView));
        text1.setText((int) examen.calcular());

    }

    public void devolverResults() {
        Intent intent=new Intent();
        intent.putExtra("valor","");
        setResult(7,intent);
        finish();
    }
}
