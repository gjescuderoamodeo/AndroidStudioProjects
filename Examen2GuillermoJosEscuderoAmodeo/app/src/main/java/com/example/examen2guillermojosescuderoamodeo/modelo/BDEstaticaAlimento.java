package com.example.examen2guillermojosescuderoamodeo.modelo;

import java.util.ArrayList;

public class BDEstaticaAlimento {

    static ArrayList<Alimento> alimentos=new ArrayList<>();

    public BDEstaticaAlimento(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
    public BDEstaticaAlimento() {

    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public void removeAlimento(int pos){
        try{
            alimentos.remove(pos);
        }catch(Exception e){

        }
    }

    public void guardarAlimento(Alimento p){
        alimentos.add(p);
    }

}
