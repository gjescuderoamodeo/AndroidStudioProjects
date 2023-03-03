package com.example.testmvvm.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmvvm.DAO.DaoExamen;
import com.example.testmvvm.entidades.Lugar;
import com.example.testmvvm.entidades.Ruta;
import com.example.testmvvm.room.ItinerarioBD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<String>> nNombres;

    //datos room
    private final MutableLiveData<ArrayList<Ruta>> DatosRoom;

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        nNombres = new MutableLiveData<>();
        nNombres.setValue(new ArrayList<String>());

        DatosRoom = new MutableLiveData<>();
        DatosRoom.setValue(new ArrayList<Ruta>());

        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<ArrayList<String>> getNombres(){return nNombres;}
    public LiveData<ArrayList<Ruta>> getRutas(){return DatosRoom;}

    public void addNombre(String nombre){
        //ArrayList<String> nombres=nNombres.getValue();
        //nombres.add(nombre);

        nNombres.getValue().add(nombre);
        nNombres.postValue(nNombres.getValue());
    }

    public void saveNombres(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> setNombres = new HashSet<>(nNombres.getValue());
        editor.putStringSet("nombres", setNombres);
        editor.apply();
    }


    public void getDB(Context context){
        List<Lugar> lugares=
                ItinerarioBD.getIinerarioBD(context).daoLugar().verLugar();
        //Log.d("CoordenadaAPP","hola mundo");
        for (Lugar al:lugares) {
            Log.d("RutasAPP", al.id + " " + al.nombre);
        }
    }
}