package com.example.toolbaralert.entidades;

import java.util.ArrayList;

public class ModeloDB {

    public static  ArrayList<Posicion> posiciones=new ArrayList<>();

    public ModeloDB() {
           }

    public static void addPosicion(Posicion p){
        posiciones.add(p);
    }

    public static void deletePosicion(String detalles){
        for(int i=0;i<posiciones.size();i++){
            if(posiciones.get(i).getDescripcion()==detalles){
                posiciones.remove(i);
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
