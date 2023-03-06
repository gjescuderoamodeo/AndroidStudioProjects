package com.example.rutas_guillermojose_escudero_amodeo.modelo;

import java.util.ArrayList;

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
