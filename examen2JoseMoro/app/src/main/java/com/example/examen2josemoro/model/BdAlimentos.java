package com.example.examen2josemoro.model;

import java.util.ArrayList;
import java.util.List;

public class BdAlimentos {

    public static ArrayList<Alimento> alimentos=new ArrayList<Alimento>();

    public  void guardarAlimento(Alimento al){
        alimentos.add(al);
    }
    public  static ArrayList<Alimento> getAlimentos(){
        return alimentos ;

}

 public  void eliminarAlimento(Alimento alimento) {

     for (int i = 0; i < alimentos.size(); i++) {
         if (alimentos.get(i).getNombre().equalsIgnoreCase(alimento.getNombre())) {
             alimentos.remove(i);
             System.out.println("Eliminado");
         }


     }
 }



}
