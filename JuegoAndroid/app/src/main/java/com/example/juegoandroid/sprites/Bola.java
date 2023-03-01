package com.example.juegoandroid.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.example.juegoandroid.Canasta;
import com.example.juegoandroid.GameView;
import com.example.juegoandroid.OnColisionListener;
import com.example.juegoandroid.Utilidades;

import java.util.Random;

public class Bola extends Sprite implements OnColisionListener{

    private Canasta game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public float rozamiento= (float) 0.98;
    public int puntuacion;

    public Bola(GameView game, int x, int y, int r, int color) {
        super();
        this.game=(Canasta)game;
        centroX=x;
        centroY=y;
        radio=r;
        this.color=color;
        velInicialX= (float) (Math.random() * 20);
        velInicialY= (float) (Math.random() * 20);
        velActualX=velInicialX;
        velActualY=velInicialY;
        puntuacion=0;
    }

    public void invisible(){
        this.visible=false;
    }

    public void visible(){
        this.visible=true;
    }

    public void resetPuntuacion(){
        this.puntuacion=0;
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
        if(s instanceof Bola){
            Bola b=(Bola)s;
            boolean col= Utilidades.colisionCirculos(centroX,centroY,radio,b.centroX,b.centroY,b.radio);
            if (!col) activa=true;
            return col;
        }else {
            Pala p=(Pala) s;
            boolean col=Utilidades.colisionCuadrados(centroX,centroY,radio,p.centroX,p.centroY);
            if (!col) activa=true;
            return col;
        }
    }

    @Override
    public void update() {
        //Se actualiza la posicion de la bola según la anterior
        if(this.color!=Color.RED){
            velActualX*=rozamiento;
            velActualY*=rozamiento;
        }
        centroX+=velActualX;
        centroY+=velActualY;
      //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
       //Se actualizan otras variables internas
        Log.d("puntu....", String.valueOf(puntuacion));

    }

    public void update2(boolean booleano) {
        //Se actualiza la posicion de la bola según la anterior
        if(this.color!=Color.RED){
            velActualX*=rozamiento;
            velActualY*=rozamiento;
        }
        if(booleano){
            this.visible();
        }else{
            this.invisible();
        }

        centroX+=velActualX;
        centroY+=velActualY;
        //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
        //Se actualizan otras variables internas
        Log.d("puntu....", String.valueOf(puntuacion));

    }


    @Override
    public void onFireColisionBorder(){
        if (this.centroX-radio<50)
            onColisionBorderEvent(OnColisionListener.LEFT);
        if (this.centroX+radio> game.getmScreenX()-50)
            onColisionBorderEvent(OnColisionListener.RIGHT);
        if (this.centroY-radio < 50)
            onColisionBorderEvent(OnColisionListener.TOP);
        if (this.centroY+radio > game.getmScreenY()-50)
            onColisionBorderEvent(OnColisionListener.BOTTOM);
    }
    @Override
    public void onColisionEvent(Sprite s) {
        Log.d("colision2", String.valueOf(s.color));
        Log.d("colision2", String.valueOf(Color.WHITE));
        Log.d("colision2", String.valueOf(this.color));
        //en caso de ser bola blanca, recolocar
        if(this.color==Color.WHITE){

            this.visible=true;
            this.velActualX=0;
            this.velActualY=0;
            this.centroX=game.mScreenX/2;
            this.centroY=game.mScreenY-250;

            if(s.color==Color.BLACK){
                puntuacion=1+puntuacion;
            }
        }

        else{
            this.visible=false;

            if(this.color==Color.RED) {
                this.visible=true;
                this.velActualX=50;
                this.velActualY=0;
                this.centroX=game.mScreenX/2;
                this.centroY=400;
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
    public  void pinta(Canvas canvas){
        paint.setColor(color);
        canvas.drawCircle(centroX,centroY,radio, paint);
    }

}
