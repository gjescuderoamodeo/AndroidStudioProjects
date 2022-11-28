package com.example.tabbedtest.modelo;

public class Coordenada {

    private int x;
    private int y;
    private String nombre;

    public Coordenada(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre=nombre;
    }

    public Coordenada() {
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
