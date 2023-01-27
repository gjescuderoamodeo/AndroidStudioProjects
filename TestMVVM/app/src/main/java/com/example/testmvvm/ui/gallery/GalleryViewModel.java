package com.example.testmvvm.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<String>> nNombres;

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        nNombres = new MutableLiveData<>();
        nNombres.setValue(new ArrayList<String>());
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<ArrayList<String>> getNombres(){return nNombres;}

    public void addNombre(String nombre){
        //ArrayList<String> nombres=mNombres.getValue();
        //
        //        nombres.add(nombre);
        nNombres.getValue().add(nombre);
    }
}