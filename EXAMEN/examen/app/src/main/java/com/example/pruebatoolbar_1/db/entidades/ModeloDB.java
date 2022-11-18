package com.example.pruebatoolbar_1.db.entidades;

import java.util.ArrayList;

public class ModeloDB {

    public static  ArrayList<Posicion> posiciones=new ArrayList<>();

    public ModeloDB() {
           }

    public static void addPosicion(Posicion p){
        posiciones.add(p);
    }

    public static void deletePosicion(String p){
        for(int i=0;i<posiciones.size();i++){
            //System.out.println("posicio"+posiciones.get(i).getDescripcion());
            //System.out.println("posicio TEST" + p + " | "+ posiciones.get(i).getDescripcion());
            if(posiciones.get(i).getDescripcion().equalsIgnoreCase(p)){
                //System.out.println("posicio TEST");
                posiciones.remove(posiciones.get(i));
            }
        }

    }

    public static void editPosicion(Posicion p, Posicion p2){
        posiciones.remove(p);
        posiciones.add(p2);
    }

    public static ArrayList<Posicion> getPosiciones() {
        return posiciones;
    }
}
