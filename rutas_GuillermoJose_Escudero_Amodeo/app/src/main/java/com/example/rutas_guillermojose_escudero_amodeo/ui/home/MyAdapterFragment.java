package com.example.rutas_guillermojose_escudero_amodeo.ui.home;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutas_guillermojose_escudero_amodeo.DaosSQLite.DaoAlimentoSQL;
import com.example.rutas_guillermojose_escudero_amodeo.MainActivity;
import com.example.rutas_guillermojose_escudero_amodeo.R;
import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;

import java.util.ArrayList;

public class MyAdapterFragment extends RecyclerView.Adapter<MyAdapterFragment.MyViewHolder> {
Context context;
ArrayList<Alimento> newArrayList;
private DaoAlimentoSQL daoAlimento;


    public MyAdapterFragment(Context context, ArrayList<Alimento> newArrayList) {
        this.context = context;
        this.newArrayList = newArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_blankq,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Alimento al = newArrayList.get(position);
    daoAlimento = new DaoAlimentoSQL(context);
    holder.tvNombre.setText(al.getNombre());
    holder.tvKcal.setText(String.valueOf(al.getKcal()));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //AlertDialog dialogo = alerta();
            //dialogo.show();

            daoAlimento.eliminarAlimento(al.getNombre());
            Toast.makeText(context, "Lugar borrado con éxito", Toast.LENGTH_SHORT).show();
        }
    });

    }



    //Alerta de dialogo
    /*private AlertDialog alerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        //View view=inflater.inflate(R.layout.activity_formulario_add_posicion,null);
        View view=inflater.inflate(R.layout.fragment_create_alimento,null);

        builder
                .setView(view)
                .setTitle("Borrar?")
                .setPositiveButton("OK",(dialog,id)->{
                    Toast.makeText(this.context,"Distancia borrada:  ", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar",(dialog,id)->{
                    dialog.cancel();
                });

        AlertDialog dialogo= builder.create();
        dialogo.show();
        return dialogo;
    }*/

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvKcal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre= (TextView) itemView.findViewById(R.id.nombreAlimentos);
            tvKcal= (TextView) itemView.findViewById(R.id.pesoAlimento);
        }


    }
}
