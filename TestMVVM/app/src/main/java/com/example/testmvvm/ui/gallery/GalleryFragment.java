package com.example.testmvvm.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testmvvm.databinding.FragmentGalleryBinding;
import com.example.testmvvm.entidades.Lugar;
import com.example.testmvvm.entidades.Ruta;
import com.example.testmvvm.room.ItinerarioBD;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    //
    GalleryViewModel galleryViewModel;

    //arrayList de nombres, que se guarda del ViewModel
    private ArrayList<String> nombres=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //instancia de un viewModel
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.Name;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto= String.valueOf(binding.Name.getText());
                galleryViewModel.addNombre(texto);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombres=galleryViewModel.getNombres().getValue();
                for(String s:nombres){
                    Log.d("MVVM_", s);
                }

                //consulta base datos a traves ViewModel
                galleryViewModel.getDB(getContext());

            }
        });

        testRoom();
        return root;
    }

    //room
    public void testRoom(){

        ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().nukeTable();
        ItinerarioBD.getIinerarioBD(this.getContext()).daoRuta().nukeTable();

        Lugar lugar1 = new Lugar(23,20,"Sevilla");
        Lugar lugar2 = new Lugar(23,20,"Cordoba");

        ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().crearLugar(lugar1);
        ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().crearLugar(lugar2);

        Lugar id1 = ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().verLugar("Sevilla");
        Lugar id2 = ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().verLugar("Cordoba");

        Ruta ruta1 = new Ruta(id1.getId(), id2.getId(), "Test");

        ItinerarioBD.getIinerarioBD(this.getContext()).daoRuta().crearRuta(ruta1);

        /*List<Lugar> lugares=
                ItinerarioBD.getIinerarioBD(this.getContext()).daoLugar().verLugar();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Lugar al:lugares) {
            Log.d("RutasAPP", al.id + " " + "");
        }


        List<Ruta> rutas=
                ItinerarioBD.getIinerarioBD(this.getContext()).daoRuta().verRuta();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Ruta al:rutas) {
            Log.d("RutasAPP", al.getNombreValue() + " " + al.destinoId + " " + al.origenId);
        }*/
    }

    //El observador para que se actualize la info al añadir nuevas cosas al array mutable
    @Override
    public void onResume() {
        super.onResume();
        galleryViewModel.getNombres().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                // Aquí puedes actualizar la interfaz de usuario con los nuevos datos
                Log.d("MVVM_", ""+ strings.toString());

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}