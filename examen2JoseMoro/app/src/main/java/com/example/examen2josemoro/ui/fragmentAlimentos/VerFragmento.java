package com.example.examen2josemoro.ui.fragmentAlimentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examen2josemoro.MainActivity;
import com.example.examen2josemoro.R;
import com.example.examen2josemoro.model.Alimento;
import com.example.examen2josemoro.model.BdAlimentos;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerFragmento extends Fragment {
 private ArrayList<Alimento>al;
 private RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static VerFragmento newInstance(String param1, String param2) {
        VerFragmento fragment = new VerFragmento();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_fragmento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        al= BdAlimentos.getAlimentos();
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        MyAdapterFragment myAdapterFragment=new MyAdapterFragment(getContext(),al);
        recyclerView.setAdapter(myAdapterFragment);
        myAdapterFragment.notifyDataSetChanged();

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

        Toast t=Toast.makeText(getContext(),"Elimado el Item", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        Toast t=Toast.makeText(getContext(),"Cancelar", Toast.LENGTH_SHORT);
        t.show();

    }



}