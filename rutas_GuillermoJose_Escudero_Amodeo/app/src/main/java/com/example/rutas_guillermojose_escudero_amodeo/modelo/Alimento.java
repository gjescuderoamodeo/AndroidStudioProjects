package com.example.rutas_guillermojose_escudero_amodeo.modelo;

import java.io.Serializable;

public class Alimento implements Serializable {
    private String nombre;
    private int kcal;

    public Alimento(String nombre, int kcal) {
        this.nombre = nombre;
        this.kcal = kcal;
    }

    public Alimento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    @Override
    public String toString() {
        return "lugar{" +
                "lugar='" + nombre + '\'' +
                ", distancia=" + kcal +
                '}';
    }
}