package com.example.videojuego.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.example.videojuego.Billar;
import com.example.videojuego.GameView;
import com.example.videojuego.OnColisionListener;
import com.example.videojuego.Utilidades;

import java.util.Random;

public class Bujero extends Sprite implements OnColisionListener {

    private Billar game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public float rozamiento= (float) 0.98;

    public Bujero(GameView game, int x, int y, int r, int color) {
        super();
        this.game=(Billar)game;
        centroX=x;
        centroY=y;
        radio=r;
        this.color=color;
        //velInicialX= (float) (Math.random() * 20);
        //velInicialY= (float) (Math.random() * 20);
        //velActualX=velInicialX;
        //velActualY=velInicialY;
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
        return false;
    }

    @Override
    public void update() {

    }


    @Override
    public void onFireColisionBorder(){
    }
    @Override
    public void onColisionEvent(Sprite s) {

    }

    @Override
    public void onColisionEvent2(Sprite2 s) {


    }

    //Invertir velocidad X
    public void invertirVelX(){
       // velActualX=-velActualX;
    }
    //Invertir velocidad Y
    public void invertirVelY(){
      //  velActualY=-velActualY;
    }


    //acelerar aleatoriamete la velocidad
    public void setRandomXVelocity(){
    }


    @Override
    public void onColisionBorderEvent(int border) {


    }

    @Override
    public  void pinta(Canvas canvas){
        paint.setColor(color);
        //paint.setStrokeWidth(8);
        // paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centroX,centroY,radio, paint);
    }

}
