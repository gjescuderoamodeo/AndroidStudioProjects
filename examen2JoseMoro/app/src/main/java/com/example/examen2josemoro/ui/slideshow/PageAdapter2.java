package com.example.examen2josemoro.ui.slideshow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.examen2josemoro.ui.fragmentAlimentos.VerFragmento;
import com.example.examen2josemoro.ui.fragmentAlimentos.createAlimento;


public class PageAdapter2 extends FragmentStateAdapter {
    public PageAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new createAlimento();
            case 1:
                return new VerFragmento();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
