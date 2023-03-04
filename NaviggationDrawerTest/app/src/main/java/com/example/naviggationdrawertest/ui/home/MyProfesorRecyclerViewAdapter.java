package com.example.naviggationdrawertest.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.naviggationdrawertest.R;
import com.example.naviggationdrawertest.modelo.Profesor;

import java.util.ArrayList;

//Para realizar un adapter personalizado, se crea una clase que herede de un Adapter genérico
public class MyProfesorRecyclerViewAdapter extends RecyclerView.Adapter<MyProfesorRecyclerViewAdapter.ViewHolder> {

    // Se definen los valores que se pasarán como lista, en nuestro caso un ArrayList de profesores
    private final ArrayList<Profesor> profesores;
    private final OnListFragmentInteractionListener mListener;

    public MyProfesorRecyclerViewAdapter(ArrayList<Profesor> items, OnListFragmentInteractionListener listener) {
        profesores = items;
        mListener = listener;
    }

    // ViewHolder es la vista que se crea a partir de la view y que nos servirá para rellenar cada item con los datos
    // en el siguiente método. Esta clase la referenciaremos como holder en el siguiente método
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profesor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    // Método importante ya que es el que dibuja cada elemento de la lista, de manera que
    // actúa lanzándose en bucle y se llama cada vez que se tiene que pintar una fila
    // el valor position va variando
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Profesor profesor;
        // holder es el elemento que recoge el objeto a mostrar según la posición
        profesor = profesores.get(position);

        // asignamos el objeto del modelo a los componentes visuales
        holder.textViewNombre.setText(profesor.getNombre());
        holder.textViewHorasLectivas.setText("Horas lectivas: " + profesor.getHoras_lectivas());
        if (profesor.isMayor_55()) {
            holder.textViewMayor55.setText("mayor 55 ");
        } else {
            holder.textViewMayor55.setText("no mayor 55");
        }

        // En este punto escuchamos los eventos y se lo pasamos al listener que teníamos referenciado desde el principio
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onListFragmentInteraction(profesor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return profesores.size();
    }

    // Clase interna que se define para mapear los contenidos que están definidos
    // en nuestro Layout para los items de RecyclerView. Es lo que permitirá al adapter
    // pintar en la pantalla cada fila de profesor.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombre;
        public final TextView textViewHorasLectivas;
        public final TextView textViewMayor55;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre = view.findViewById(R.id.nombre);
            textViewHorasLectivas = view.findViewById(R.id.horasLectivas);
            textViewMayor55 = view.findViewById(R.id.mayor55);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }

    // Interfaz que debe ser implementada por el fragmento que contenga el RecyclerView
    // para que este adapter pueda comunicarle eventos de interacción con los items.
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Profesor profesor);
    }
}

