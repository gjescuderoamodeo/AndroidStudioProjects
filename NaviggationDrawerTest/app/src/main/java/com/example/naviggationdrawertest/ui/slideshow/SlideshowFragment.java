package com.example.naviggationdrawertest.ui.slideshow;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.naviggationdrawertest.R;
import com.example.naviggationdrawertest.databinding.FragmentSlideshowBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

//https://developer.android.com/guide/navigation/navigation-swipe-view-2#java
public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private ViewPager2 viewPager2;
    private TabLayout tabs;
    private PageAdapter2 pageAdapter2; //Es una clase dentro de slideshow

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        //binding puntero al fragment // poner binding.
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //referencia
        viewPager2 = binding.viewPager2;
        pageAdapter2=new PageAdapter2(this.getActivity());//lo de las pestañas
        viewPager2.setAdapter(pageAdapter2);

        tabs=binding.tabs;

        new TabLayoutMediator(tabs,viewPager2,(tab, position)->{
         if(position==0)tab.setText("panel1"); //nombres paneles
         if(position==1)tab.setText("panel2");
         if(position==2)tab.setText("panel3");
        }).attach();

        //no hace falta
        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //menu
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //menu
    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //en main tengo el menu principal
        //inflater.inflate(R.menu.main, menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                Log.d("Opcion_", "opcion1");
                return true;
            case R.id.option2:
                añadirPosiciones();
                return true;
            case R.id.option3:
                // Acción para la opción 3
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //

    private void añadirPosiciones() {
        AlertDialog dialogo = alerta();
        dialogo.show();
    }

    //Alerta de dialogo
    private AlertDialog alerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_formulario_add_posicion,null);

        builder
                .setView(view)
                .setTitle("Información")
                .setPositiveButton("OK",(dialog,id)->{
                    EditText editText=(EditText)(view.findViewById(R.id.descripcion));
                    Editable texto=editText.getText();
                    //p.setDescripcion(texto.toString()); p es el atributo de un objeto si le pasara uno
                    Toast.makeText(requireContext(),"Posición guardada:  "+ texto, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar",(dialog,id)->{
                    dialog.cancel();
                });

        AlertDialog dialogo= builder.create();
        dialogo.show();
        return dialogo;
    }*/

}