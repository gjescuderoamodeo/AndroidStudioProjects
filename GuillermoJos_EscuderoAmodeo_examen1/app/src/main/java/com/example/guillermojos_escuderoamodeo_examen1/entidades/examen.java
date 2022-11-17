package com.example.guillermojos_escuderoamodeo_examen1.entidades;

public class examen {

    private long n1;
    private long n2;
    private long distancia;
    private long tiempo;

    public examen() {
    }

    public examen(long n1, long n2, long distancia, long tiempo) {
        this.n1 = n1;
        this.n2 = n2;
        this.distancia=distancia;
        this.tiempo=tiempo;
    }

    public long getN1() {
        return n1;
    }

    public void setN1(long n1) {
        this.n1 = n1;
    }

    public long getN2() {
        return n2;
    }

    public void setN2(long n1) {
        this.n2 = n2;
    }

    public long getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(String descripcion) {
        this.tiempo = tiempo;
    }

    public double calcular() {
        return ((this.getN1()/this.getN2()*(this.distancia/this.tiempo)));
    }
}
