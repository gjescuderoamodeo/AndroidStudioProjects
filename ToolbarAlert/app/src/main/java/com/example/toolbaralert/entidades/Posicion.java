package com.example.toolbaralert.entidades;

public class Posicion {

    private long longitud;
    private long latitud;
    private String descripcion;

    public Posicion() {
    }

    public Posicion(long longitud, long latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
