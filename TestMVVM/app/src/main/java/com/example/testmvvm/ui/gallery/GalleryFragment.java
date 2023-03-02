package com.example.testmvvm.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testmvvm.R;
import com.example.testmvvm.databinding.FragmentGalleryBinding;
import com.example.testmvvm.entidades.Lugar;
import com.example.testmvvm.entidades.Ruta;
import com.example.testmvvm.room.ItinerarioBD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel galleryViewModel;
    private ArrayList<String> nombres = new ArrayList<>();

    // Contexto para acceder a SharedPreferences
    private Context context;

    // Nombre del archivo donde se guardarán los datos en SharedPreferences
    private static final String SHARED_PREFERENCES_FILE_NAME = "gallery_fragment_data";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Instanciamos el ViewModel y lo asignamos a la variable miembro galleryViewModel
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //menu
        setHasOptionsMenu(true);

        final TextView textView = binding.Name;

        // Observamos el LiveData del ViewModel para actualizar el TextView
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = String.valueOf(binding.Name.getText());
                galleryViewModel.addNombre(texto);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Obtenemos los nombres desde el ViewModel
                nombres = galleryViewModel.getNombres().getValue();
                for (String s : nombres) {
                    Log.d("MVVM_", s);
                }

                // Obtenemos los datos desde SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                String sharedPrefData = sharedPreferences.getString("data", "");
                Log.d("SharedPreferences_", sharedPrefData);
            }
        });

        // Asignamos el contexto actual a la variable miembro context
        context = getContext();

        return root;
    }

    //menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_gallery, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                Log.d("Opcion_", "opcion1");
                return true;
            case R.id.option2:
                // Acción para la opción 2
                return true;
            case R.id.option3:
                // Acción para la opción 3
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //

    @Override
    public void onResume() {
        super.onResume();

        // Observamos el LiveData del ViewModel para mostrar los cambios
        galleryViewModel.getNombres().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                Log.d("MVVM_", "" + strings.toString());

                // Guardamos los datos en SharedPreferences cuando se producen cambios en los nombres
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("data", strings.toString());
                editor.apply();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    //room
    /*public void testRoom(){

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

        List<Lugar> lugares=
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
        }
    }*/
}