package com.example.rutas_guillermojose_escudero_amodeo.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.DaoAlimentoSQL;
import com.example.rutas_guillermojose_escudero_amodeo.R;
import com.example.rutas_guillermojose_escudero_amodeo.databinding.FragmentGalleryBinding;
import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;
import com.example.rutas_guillermojose_escudero_amodeo.ui.home.MyAdapterFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //saco la distancia de la consulta sqlite y le doy ese valor a textView3
        int distanciaT = sacarDistanciaT();
        TextView textView = binding.textView3;
        textView.setText(String.valueOf(distanciaT));


        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private int sacarDistanciaT() {
        DaoAlimentoSQL daoAl = new DaoAlimentoSQL(getContext());
        int distanciaT = daoAl.distanciaTotal();
        Log.d("Opcion_", String.valueOf(distanciaT));
        return distanciaT;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}