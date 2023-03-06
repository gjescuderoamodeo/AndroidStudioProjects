package com.example.rutas_guillermojose_escudero_amodeo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutas_guillermojose_escudero_amodeo.MainActivity;
import com.example.rutas_guillermojose_escudero_amodeo.R;
import com.example.rutas_guillermojose_escudero_amodeo.modelo.Alimento;

import java.util.ArrayList;

public class MyAdapterFragment extends RecyclerView.Adapter<MyAdapterFragment.MyViewHolder> {
Context context;
ArrayList<Alimento> newArrayList;


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
        System.out.println(al.getNombre());
    holder.tvNombre.setText(al.getNombre());
    holder.tvKcal.setText(String.valueOf(al.getKcal()));
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent intent=new Intent(holder.itemView.getContext(), MainActivity.class);
        intent.putExtra("itemDetail",al);
        holder.itemView.getContext().startActivity(intent);
        }
    });

    }

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
