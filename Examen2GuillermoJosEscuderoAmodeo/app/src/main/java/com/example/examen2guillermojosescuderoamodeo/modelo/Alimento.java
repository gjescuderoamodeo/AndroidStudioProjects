package com.example.examen2guillermojosescuderoamodeo.modelo;

public class Alimento {

    private int kcal;
    private String nombre;

    public Alimento(int kcal, String nombre) {
        this.kcal = kcal;
        this.nombre=nombre;
    }

    public Alimento() {
    }


    public int getkcal() {
        return kcal;
    }

    public void setkcal(int x) {
        this.kcal = x;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
