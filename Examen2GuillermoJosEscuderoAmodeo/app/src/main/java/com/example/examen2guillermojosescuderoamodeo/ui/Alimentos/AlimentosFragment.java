package com.example.examen2guillermojosescuderoamodeo.ui.Alimentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen2guillermojosescuderoamodeo.databinding.FragmentAlimentosBinding;

public class AlimentosFragment extends Fragment {

    private FragmentAlimentosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlimentosViewModel alimentosViewModel =
                new ViewModelProvider(this).get(AlimentosViewModel.class);

        binding = FragmentAlimentosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        alimentosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}