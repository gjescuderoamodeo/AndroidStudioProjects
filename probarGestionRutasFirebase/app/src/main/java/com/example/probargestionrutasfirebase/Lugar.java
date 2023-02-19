package com.example.probargestionrutasfirebase;

public class Lugar {
        private String nombre;
        private String descripcion;

        public Lugar() {}

        public Lugar(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }

