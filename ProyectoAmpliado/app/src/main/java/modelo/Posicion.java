package modelo;

public class Posicion {



    private int x;
    private int y;
    private String descripcion;

    public Posicion(int x, int y, String descripcion){
        this.x=x;
        this.y=y;
        this.descripcion=descripcion;
    }

    public Posicion(){
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
