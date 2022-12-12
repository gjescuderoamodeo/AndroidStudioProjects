package com.example.examen2guillermojosescuderoamodeo.ui.Alimentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlimentosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlimentosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is alimentos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}