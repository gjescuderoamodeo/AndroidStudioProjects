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
import com.example.testmvvm.entidades.RutaExamen;
import com.example.testmvvm.room.ItinerarioBD;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel galleryViewModel;
    private ArrayList<String> nombres = new ArrayList<>();

    //FIRESTORE
    private FirebaseFirestore db;
    private CollectionReference collectionReference;

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

        //firestore
        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("ruta");

        //final TextView textView = binding.Name;

        // Observamos el LiveData del ViewModel para actualizar el TextView
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //al pulsar el boton1, se añaden datos al firebase
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String longitud = String.valueOf(binding.Name.getText());
                String lat = String.valueOf(binding.lat.getText());
                String rumbo = String.valueOf(binding.rumbo.getText());
                String distancia = String.valueOf(binding.distancia.getText());

                RutaExamen rutaExamen =
                        new RutaExamen(Integer.valueOf(longitud),Integer.valueOf(lat),rumbo,Integer.valueOf(distancia));

                addRuta(rutaExamen);

                //galleryViewModel.addNombre(texto);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Obtenemos los nombres desde el ViewModel
                /*nombres = galleryViewModel.getNombres().getValue();
                for (String s : nombres) {
                    Log.d("MVVM_", s);
                }*/

                //Log de rutas
                getRutas();


                // Obtenemos los datos desde SharedPreferences
                /*SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                String sharedPrefData = sharedPreferences.getString("data", "");
                Log.d("SharedPreferences_", sharedPrefData);*/
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

    //Get Rutas 3 Examen
    public void getRutas(){
        collectionReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<RutaExamen> rutas = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    RutaExamen ruta = document.toObject(RutaExamen.class);
                    rutas.add(ruta);
                    Log.d("TAG", "Rutas: " + ruta.getAll());
                }
                //Log.d("TAG", "Rutas: " + rutas);
            } else {
                Log.w("TAG", "Error getting documents.", task.getException());
            }
        });

    }

    public void addRuta(RutaExamen ruta){
        collectionReference.add(ruta)
                .addOnSuccessListener(documentReference -> {
                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "Error adding document", e);
                });
    }

}