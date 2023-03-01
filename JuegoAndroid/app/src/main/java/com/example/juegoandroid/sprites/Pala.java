package com.example.juegoandroid.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.juegoandroid.Canasta;
import com.example.juegoandroid.GameView;
import com.example.juegoandroid.OnColisionListener;
import com.example.juegoandroid.Utilidades;

import java.util.Random;

public  class Pala extends Sprite implements OnColisionListener {

    private Canasta game;
    public float centroX,centroY,radio, left, top, right, bottom;
    public boolean activa=true;

    public Pala(GameView game, int x, int y, float left, float top, float right, float bottom){
        super();
        this.game=(Canasta)game;
        centroX=x;
        centroY=y;
        velInicialX= 0;
        velInicialY= 0;
        velActualX=velInicialX;
        velActualY=velInicialY;

        this.left=left;
        this.right=right;
        this.top=top;
        this.bottom=bottom;
    }

    @Override
    public void setup() {
        this.velActualX=0;
        this.velActualY=0;
       /* this.velActualX=velInicialX* game.factor_mov;
        this.velActualY=velInicialY* game.factor_mov;
        */

    }

    @Override
    public boolean colision(Sprite s){
        Bola b=(Bola)s;
        boolean col= Utilidades.colisionCirculos(centroX,centroY,radio,b.centroX,b.centroY,b.radio);
        if (!col) activa=true;
        return col;
    }

    @Override
    public void update() {

    }

    @Override
    public void update2(boolean visible) {

    }


    @Override
    public void onFireColisionBorder(){
    }
    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Bola) {
            if(activa){
                Bola b=(Bola)s;
                float dy=(float)(b.centroY-centroY);
                float dx=(float)(b.centroX-centroX);
                float ang=(float)Math.atan2(dy,dx);
                double cosa=Math.cos(ang);
                double sina=Math.sin(ang);
                float vx2=(float)(cosa*b.velActualX+sina*b.velActualY);
                float vy1=(float)(cosa*b.velActualY-sina*b.velActualX);
                float vx1=(float)(cosa*velActualX+sina*velActualY);
                float vy2=(float)(cosa*velActualY-sina*velActualX);
                b.velActualX=(float)(cosa*vx1-sina*vy1);
                b.velActualY=(float)(cosa*vy1+sina*vx1);
                velActualX=(float)(cosa*vx2-sina*vy2);
                velActualY=(float)(cosa*vy2+sina*vx2);
            }
        }
    }


    //Invertir velocidad X
    public void invertirVelX(){
        velActualX=-velActualX;
    }
    //Invertir velocidad Y
    public void invertirVelY(){
        velActualY=-velActualY;
    }


    //acelerar aleatoriamete la velocidad
    public void setRandomXVelocity(){
        Random random=new Random();
        int addVelocity=random.nextInt(2);
        this.velActualX+=addVelocity;
        if (addVelocity==0)invertirVelX();
    }


    @Override
    public void onColisionBorderEvent(int border) {

        switch (border){
            case OnColisionListener.TOP:
                velActualY=-velActualY;
                break;
            case OnColisionListener.BOTTOM:
                velActualY=-velActualY;
                break;
            case OnColisionListener.RIGHT:
                velActualX=-velActualX;
                break;
            case OnColisionListener.LEFT:
                velActualX=-velActualX;
                break;
            default:

                break;
        }
    }

    @Override
    public void pinta(Canvas canvas) {

        //dibujamos
        paint.setColor(Color.argb(255, 108, 59, 42));
        //canvas.drawRect(80, 0, 0, 1200, paint);
        canvas.drawRect(this.left, this.top, this.right, this.bottom, paint);
    }


}
