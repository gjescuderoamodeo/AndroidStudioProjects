package com.example.naviggationdrawertest.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.naviggationdrawertest.DaosSQLite.DaoAlimentoSQL;
import com.example.naviggationdrawertest.MainActivity;
import com.example.naviggationdrawertest.R;
import com.example.naviggationdrawertest.modelo.Alimento;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createAlimento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createAlimento extends Fragment {
    private Alimento al;
    private EditText nombre, kcal;
    private Button btn1;
    private String nombreAl = "";
    private int kcalAl = 0;
    private DaoAlimentoSQL daoAlimento;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public createAlimento() {
        // Required empty public constructor
    }

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
        daoAlimento = new DaoAlimentoSQL(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        al = new Alimento();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_alimento, container, false);
        nombre = view.findViewById(R.id.nombreAlimento);
        kcal = view.findViewById(R.id.kcalAl);
        btn1 = view.findViewById(R.id.addAl);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aceptar();
            }
        });
        return view;
    }

    public void aceptar() {
        nombreAl = nombre.getText().toString();
        al.setNombre(nombreAl);
        kcalAl = Integer.parseInt(kcal.getText().toString());
        al.setKcal(kcalAl);

        // Creamos el nuevo alimento
        Alimento alimento = new Alimento();
        alimento.setKcal(kcalAl);
        alimento.setNombre(nombreAl);

        // Guardamos el nuevo alimento en SQLite
        daoAlimento.crearAlimento(alimento);

        // Notificamos al usuario que se ha creado un nuevo alimento
        Toast.makeText(getContext(), "Alimento creado con éxito", Toast.LENGTH_SHORT).show();
    }

    public void cancelar() {
        Toast t = Toast.makeText(getContext(),"Cancelar", Toast.LENGTH_SHORT);
        t.show();
    }
}



