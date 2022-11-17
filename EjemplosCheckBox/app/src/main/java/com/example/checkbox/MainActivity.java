package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void marcar(View view){

        switch(view.getId()){
            case R.id.checkBox1:
                Toast.makeText(context: this, text:"Expulsión", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox2:
                CheckBox chb = (CheckBox) findViewById(R.id.checkBox1);
                chb.setChecked(true);

                Toast.makeText(context: this, text:"grave", Toast.LENGTH_LONG).show();
                break;
        }


    }

}