package com.example.examen2josemoro.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.examen2josemoro.databinding.FragmentSlideshowBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private ViewPager2 viewPager2;
    private TabLayout tabs;
    private PageAdapter2 page;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewPager2=binding.viewPager2;
        page=new PageAdapter2(this.getActivity());
        viewPager2.setAdapter(page);
        tabs=binding.tabs;
        new TabLayoutMediator(tabs,viewPager2,(tab, position) -> {
            if(position==0)tab.setText("Crear");
            if(position==1)tab.setText("Ver");
        }).attach();
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}