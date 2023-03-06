package com.example.rutas_guillermojose_escudero_amodeo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.Dao;
import com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.DaoAlimentoSQL;
import com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.GestionAlimentossBD;
import com.example.rutas_guillermojose_escudero_amodeo.R;
import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerFragmento extends Fragment {
    private ArrayList<Alimento> al;
    private RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VerFragmento() {
        // Required empty public constructor
    }

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

        Dao.setGestorBD(new GestionAlimentossBD(getContext()));
        Dao.gestorBd.getWritableDatabase();

        //DaoAlimentoSQL daoAl = new DaoAlimentoSQL(getContext());
        //Alimento alimento = new Alimento();
        //alimento.setNombre("aa");
        //alimento.setKcal(220);

        //daoAl.crearAlimento(alimento);
    }

    public void addAlimento(Alimento alimento) {
        al.add(alimento);
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
        actualizarRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        actualizarRecyclerView();
    }

    private void actualizarRecyclerView() {
        DaoAlimentoSQL daoAl = new DaoAlimentoSQL(getContext());
        ArrayList<Alimento> al = daoAl.verAlimentos();
        Log.d("Opcion_", al.toString());

        recyclerView = getView().findViewById(R.id.recycleView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        MyAdapterFragment myAdapterFragment = new MyAdapterFragment(getContext(), al);
        recyclerView.setAdapter(myAdapterFragment);
        myAdapterFragment.notifyDataSetChanged();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycleView);
        MyAdapterFragment adapter = (MyAdapterFragment) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new MyAdapterFragment(getContext(), al);
            recyclerView.setAdapter(adapter);
        }
    }
}
