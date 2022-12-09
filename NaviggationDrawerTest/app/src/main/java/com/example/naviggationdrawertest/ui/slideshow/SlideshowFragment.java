package com.example.naviggationdrawertest.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

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
         if(position==0)tab.setText("panel1");
         if(position==1)tab.setText("panel2");
        }).attach();

        //no hace falta
        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}