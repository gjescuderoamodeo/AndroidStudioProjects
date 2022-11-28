package com.example.tabbedtest.modelo;

import java.util.ArrayList;

public class BDEstaticaCoordenada {

    static ArrayList<Coordenada> coordenadas=new ArrayList<>();

    public BDEstaticaCoordenada(ArrayList<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }
    public BDEstaticaCoordenada() {
       // coordenadas.add(new Coordenada(0,10));
       // coordenadas.add(new Coordenada(11,20));
       // coordenadas.add(new Coordenada(21,30));

    }

    public ArrayList<Coordenada> getProfesores() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void removeCoordenada(int pos){
        try{
            coordenadas.remove(pos);
        }catch(Exception e){

        }
    }

    public void guardarCoordenada(Coordenada p){
        coordenadas.add(p);
    }

}
