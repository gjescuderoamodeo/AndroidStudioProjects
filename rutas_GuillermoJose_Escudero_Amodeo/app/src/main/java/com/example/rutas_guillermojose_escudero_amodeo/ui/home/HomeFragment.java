package com.example.rutas_guillermojose_escudero_amodeo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.rutas_guillermojose_escudero_amodeo.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 viewPager2;
    private TabLayout tabs;
    private PageAdapter2 pageAdapter2; //Es una clase dentro de home

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //referencia
        viewPager2 = binding.viewPager2;
        pageAdapter2=new PageAdapter2(this.getActivity());//lo de las pestañas
        viewPager2.setAdapter(pageAdapter2);

        tabs=binding.tabs;

        new TabLayoutMediator(tabs,viewPager2,(tab, position)->{
            if(position==0)tab.setText("añadir lugar"); //nombres paneles
            if(position==1)tab.setText("ver lugares");
        }).attach();


        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}