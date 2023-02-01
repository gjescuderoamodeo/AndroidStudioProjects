package com.example.videojuego.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.videojuego.GameView;
import com.example.videojuego.OnColisionListener;

public  class Pala extends SpriteRect implements OnColisionListener {

    public final int STOP=0;
    public final int LEFT=1;
    public final int RIGHT=2;
    public int estadoPala=STOP;

    public Pala(int x, int y){

        super(x,y);

        //longitud y anchura de la pala
        //ancho=mScreenX/8;
        //alto=mScreenY/25;

        ancho=1000;
        alto=5000;

        //Posición inicial de la pala
        mXCoord=mScreenX/2;
        mYCoord=mScreenY-100;
        //tamaño del rectángulo que pinta a la pala
        mRect=new RectF(mXCoord, mYCoord, mXCoord+ ancho, mYCoord+ alto);
        //Velocidad de la pala
        velInicialX=0;
        velActualX=velInicialX;

    }

    @Override
    public boolean colision(Sprite2 s) {
        return false;
    }
    public void setEstadoPala(int estadoPala) {
        this.estadoPala = estadoPala;
    }
    public void icrementaVelocidad(){

        velActualX*=1.2f;

    }
    public void reset() {

        velActualX=velInicialX;


    }

    @Override
    public void update(GameView game, float fps) {

        switch (estadoPala){
            case STOP: break;
            case LEFT:  mXCoord=mXCoord-velActualX;break;
            case RIGHT: mXCoord=mXCoord+velActualX;break;
        }

        //Controlamos los bordes
      if (colisionBordeLeft())  mXCoord=0;
      if (colisionBordeRight())
          mXCoord=mScreenX-(mRect.right-mRect.left);
      //  if (colisionBordeLeft()||colisionBordeRight())  setEstadoPala(STOP);//mXCoord=0;

        //Damos las coordenadas del Rectángulo. Solo se han modificado el eje X
        mRect.left=mXCoord;
        mRect.right=mXCoord+ ancho;
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }


    @Override
    public void pinta(Canvas canvas) {

        //dibujamos
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawRect(getRect(), paint);

    }

    @Override
    public void onColisionEvent(Sprite s) {

    }

    @Override
    public void onColisionEvent2(Sprite2 s) {

    }

    @Override
    public void onColisionBorderEvent(int border) {

    }
}
