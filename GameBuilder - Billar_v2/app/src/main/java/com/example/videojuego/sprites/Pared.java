package com.example.videojuego.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.videojuego.GameView;

public class Pared extends Sprite{
    public Pared(GameView game) {
        super();
    }

    @Override
    public void onColisionEvent(Sprite s) {

    }

    @Override
    public void onColisionBorderEvent(int border) {

    }

    @Override
    public void onFireColisionBorder() {

    }

    @Override
    public boolean colision(Sprite s) {
        return false;
    }

    @Override
    public void pinta(Canvas canvas) {
        //dibujamos
        paint.setColor(Color.argb(255, 255, 255, 255));
        //canvas.drawRect(getRect(), paint);
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }
}
