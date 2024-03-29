package com.example.rutas_guillermojose_escudero_amodeo.modelo;

import java.util.ArrayList;

public class BDEstaticaProfesores {

    static ArrayList<Profesor> profesores=new ArrayList<>();

    public BDEstaticaProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }
    public BDEstaticaProfesores() {
        profesores.add(new Profesor("@tools:sample/avatars[0]","Pedro",13,false));
        profesores.add(new Profesor(null,"Adolfo",15,false));
        profesores.add(new Profesor(null,"Dioni",10,false));
        profesores.add(new Profesor(null,"María José",18,false));
        profesores.add(new Profesor(null,"Carmen",18,false));
        profesores.add(new Profesor(null,"Victoria",16,true));

    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void removeProfesor(int pos){
        try{
            profesores.remove(pos);
        }catch(Exception e){

        }
    }

    public void guardarProfesor(Profesor p){
        profesores.add(p);
    }

}
