package com.example.examen2guillermojosescuderoamodeo.ui.Alimentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen2guillermojosescuderoamodeo.R;
import com.example.examen2guillermojosescuderoamodeo.modelo.Alimento;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_alimentos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_alimentos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<Alimento> alimentos=new ArrayList<Alimento>();;

    public add_alimentos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_alimentos.
     */
    // TODO: Rename and change types and number of parameters
    public static add_alimentos newInstance(String param1, String param2) {
        add_alimentos fragment = new add_alimentos();
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
        View view = inflater.inflate(R.layout.fragment_add_alimentos,
                container, false);
        Button button = (Button) view.findViewById(R.id.añadir);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String kcal = ((TextView) view.findViewById(R.id.kcal)).getText().toString();
                String nombre = ((TextView) view.findViewById(R.id.nombre)).getText().toString();


                alimentos.add(new Alimento(Integer.parseInt(kcal),nombre));
                System.out.println("Alimentos: " + alimentos);
            }
        });
        //return view;

        return inflater.inflate(R.layout.fragment_add_alimentos, container, false);
    }

    public void addAlimento(View view) {
        String kcal = ((TextView) view.findViewById(R.id.kcal)).getText().toString();
        String nombre = ((TextView) view.findViewById(R.id.nombre)).getText().toString();


        this.alimentos.add(new Alimento(Integer.parseInt(kcal),nombre));
        //Toast.makeText(this,"Ajustes guardados", Toast.LENGTH_SHORT).show();
        //System.out.println("Alimentos: " + this.alimentos);
    }
}