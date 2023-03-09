package com.example.testmvvm.entidades;

public class RutaExamen {
    private int longInicial;
    private int latInicial;
    private String rumbo;
    private int distancia;

    public RutaExamen() {}

    public RutaExamen(int longInicial, int latInicial, String rumbo, int distancia) {
        this.longInicial = longInicial;
        this.latInicial = latInicial;
        this.rumbo=rumbo;
        this.distancia=distancia;
    }

    public int getlongInicial() {
        return longInicial;
    }

    public void setlongInicial(int longInicial) {
        this.longInicial = longInicial;
    }

    public int getlatInicial() {
        return latInicial;
    }

    public void setlatInicial(int latInicial) {
        this.latInicial = latInicial;
    }

    public int getdistancia() {
        return distancia;
    }

    public String getAll() {
        return "distancia" + String.valueOf(distancia) + " Rumbo: " + rumbo;
    }

    public void setdistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getrumbo() {
        return rumbo;
    }

    public void setrumbo(String rumbo) {
        this.rumbo = rumbo;
    }

    @Override
    public String toString() {
        return "RutaExamen{" +
                "longInicial=" + longInicial +
                ", latInicial=" + latInicial +
                ", rumbo='" + rumbo + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
