package com.example.examen2josemoro.ui.fragmentAlimentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen2josemoro.MainActivity;
import com.example.examen2josemoro.R;
import com.example.examen2josemoro.model.Alimento;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createAlimento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createAlimento extends Fragment {
private Alimento al;
private EditText nombre,kcal;
private Button btn1;
private String nombreAl="";
private int kcalAl=0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public createAlimento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createAlimento.
     */
    // TODO: Rename and change types and number of parameters
    public static createAlimento newInstance(String param1, String param2) {
        createAlimento fragment = new createAlimento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        al= new Alimento();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_alimento, container, false);
        nombre=(EditText) view.findViewById(R.id.nombreAlimento);
        kcal=(EditText) view.findViewById(R.id.kcalAl);
        btn1=(Button) view.findViewById(R.id.addAl);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* nombreAl=nombre.getText().toString();
                 al.setNombre(nombreAl);
                kcalAl=Integer.parseInt(kcal.getText().toString());
                al.setKcal(kcalAl);
                Log.d("Alimenot",al.toString());
                Intent intent=new Intent(getContext(), MainActivity.class);
                intent.putExtra("alimento",(Serializable) al);
                startActivity(intent);*/
                alertDialogo();

            }
        });
        return view;
    }
    public void alertDialogo(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Confirma si quieres crear el objeto ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();
    }

    public void aceptar() {
        nombreAl=nombre.getText().toString();
        al.setNombre(nombreAl);
        kcalAl=Integer.parseInt(kcal.getText().toString());
        al.setKcal(kcalAl);
        Log.d("Alimenot",al.toString());
        Intent intent=new Intent(getContext(), MainActivity.class);
        intent.putExtra("alimento",(Serializable) al);
        startActivity(intent);
        Toast t=Toast.makeText(getContext(),"Creado el Item", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        Toast t=Toast.makeText(getContext(),"Cancelar", Toast.LENGTH_SHORT);
        t.show();

    }

    }


