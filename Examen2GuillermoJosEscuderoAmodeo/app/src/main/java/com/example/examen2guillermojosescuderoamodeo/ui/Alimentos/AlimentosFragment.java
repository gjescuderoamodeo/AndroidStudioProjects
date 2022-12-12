package com.example.examen2guillermojosescuderoamodeo.ui.Alimentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.examen2guillermojosescuderoamodeo.databinding.FragmentAlimentosBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AlimentosFragment extends Fragment {

    private FragmentAlimentosBinding binding;

    private ViewPager2 viewPager2;
    private TabLayout tabs;
    private PageAdapter2 pageAdapter2; //Es una clase dentro de slideshow

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AlimentosViewModel slideshowViewModel =
                new ViewModelProvider(this).get(AlimentosViewModel.class);

        //binding puntero al fragment // poner binding.
        binding = FragmentAlimentosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //referencia
        viewPager2 = binding.viewPager2;
        pageAdapter2=new PageAdapter2(this.getActivity());//lo de las pestañas
        viewPager2.setAdapter(pageAdapter2);

        tabs=binding.tabs;

        new TabLayoutMediator(tabs,viewPager2,(tab, position)->{
            if(position==0)tab.setText("Ver Alimentos");
            if(position==1)tab.setText("Añadir Alimentos");
        }).attach();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}