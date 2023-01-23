package com.example.videojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.videojuego.sprites.Bola;

import com.example.videojuego.sprites.Sprite;

import java.util.LinkedList;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1, bola2, bola3, bola4, bola5, bola6;



    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;


    public Billar(Context context, int x, int y) {
        super(context,x,y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {
        bola1 = new Bola(this,100, 100, 50,Color.WHITE);
        bola2 = new Bola(this, 200, 100, 50,Color.RED);
        bola3 = new Bola(this, 300, 100, 50,Color.BLUE);
        bola4 = new Bola(this, 400, 100, 50,Color.YELLOW);
        bola5 = new Bola(this, 500, 100, 50,Color.BLACK);
        bola6= new Bola(this, 590,100, 50,Color.GREEN);
        actores.add(bola1);  bola1.setup();
        actores.add(bola2);  bola2.setup();
        actores.add(bola3);  bola3.setup();
        actores.add(bola4);  bola4.setup();
        actores.add(bola5);  bola5.setup();
        actores.add(bola6);  bola6.setup();
        if (vidas == 0) {
            puntuacion = 0;
            vidas = 3;
        }
    }
    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if(actor.isVisible())
               actor.update();
        }

    }


    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        canvas.drawColor(Color.argb(255, 20, 128, 188));
        synchronized(actores) {
            for (Sprite actor : actores) {
                    actor.pinta(canvas);
            }
        }
        //dibujamos puntuacion y vidas
        paint.setTextSize(30);
        canvas.drawText("Factor_mov: " + this.factor_mov + "  Vidas: " + actores.size(), 10, 50, paint);
    }


  //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {

    }


}
