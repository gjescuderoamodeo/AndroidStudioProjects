package com.example.naviggationdrawertest.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.naviggationdrawertest.databinding.FragmentSlideshowBinding;

//https://developer.android.com/guide/navigation/navigation-swipe-view-2#java
public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    //private ViewPager2 vieesPager2;
    //Tablayaut
    //PagerAdapter2 //Es una clase dentro de slideshow

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        //binding puntero al fragment // poner binding.
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //referencia viewpager2 ; binding.viewPager2
        //pagerAdapter2=new pagerAdapter2(this.getActivity() )//lo de las pestañas
        //viewpager2.setAdapter(pagerAdapter2)

        //tabs=binding.tabs

        //new TabLayautMediator(tabs,viewPager2,(tab,posicion)->{
        // if(position==0)tab.setText("");
        // if(position==0)...
        // }).attach

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