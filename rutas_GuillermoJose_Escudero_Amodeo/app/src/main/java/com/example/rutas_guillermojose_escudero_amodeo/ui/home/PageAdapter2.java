package com.example.rutas_guillermojose_escudero_amodeo.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rutas_guillermojose_escudero_amodeo.ui.slideshow.SlideshowFragment;

public class PageAdapter2 extends FragmentStateAdapter {


    public PageAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new DemoObjectFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);

        switch(position){
            case 0:
                //return new ProfesorFragment();
                //return new HomeFragment();
                return new createAlimento();
            case 1:
                return new VerFragmento();
        }

        return null;

    }

    //aqui se le añaden más tabs
    @Override
    public int getItemCount() {
        return 2;
    }
}
