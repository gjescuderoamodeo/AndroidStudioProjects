package com.example.juegoandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.juegoandroid.sprites.Bola;
import com.example.juegoandroid.sprites.Pala;
import com.example.juegoandroid.sprites.Sprite;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1,bola2;
    Pala pared1,pared2,pared3,pared4;
    //

    float lineX1,lineY1,lineX2,lineY2;
    boolean estaDentro=false;
    boolean apunta=false;



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

        //Blanca
        bola1 = new Bola(this,300, 500, 50,Color.WHITE);
        actores.add(bola1);  bola1.setup();
        //bola2 = new Bola(this,300, 500, 50,Color.WHITE);
        //actores.add(bola2);  bola2.setup();
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
        canvas.drawColor(Color.argb(255, 205, 92, 92));
        synchronized(actores) {
            for (Sprite actor : actores) {
                    actor.pinta(canvas);
            }
        }

        //pintar marcos
        //canvas.drawColor(Color.argb(255, 0, 102, 200));

        //dibujamos puntuacion y vidas
        paint.setTextSize(30);
        //canvas.drawText("Factor_mov: " + this.factor_mov + "  Vidas: " + actores.size(), 10, 50, paint);
        canvas.drawText("EL JUEGO DEL BILLAR: ", 150, 40, paint);
        paint.setTextSize(800);
        if(estaDentro){
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(5);
            canvas.drawLine(bola1.centroX,bola1.centroY,lineX2,lineY2,paint);
            /*if (bola1.getVelActualX()>50){
                bola1.setVelActualX(50);
            }
            if (bola1.getVelActualY()>50){
                bola1.setVelActualY(50);
            }*/
            //Log.d("billar",bola1.getVelActualX()+"----"+bola1.getVelActualY());
          if(apunta){
              paint.setColor(Color.RED);
              canvas.drawLine(bola1.centroX,bola1.centroY,(bola1.centroX-lineX2)*1000,(bola1.centroY-lineY2)*1000,paint);

          }

        }

    }

  //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1=event.getX();
        lineY1=event.getY();
        if (Utilidades.distancia(lineX1,lineY1,bola1.centroX,bola1.centroY)<bola1.radio){
            estaDentro=true;
            lineX1=bola1.centroX;
            lineY1=bola1.centroY;
            lineX2=bola1.centroX;
            lineY2=bola1.centroY;

        }
        Log.d("billar","X: "+lineX1+" Y: "+lineY1);

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        if(estaDentro){
            lineX2=event.getX();
            lineY2=event.getY();
            bola1.setVelActualX((lineX1-lineX2)/10);
            bola1.setVelActualY((lineY1-lineY2)/10);
            estaDentro=false;
            apunta=false;
        }

        /*if (bola1.getVelActualX()>10){
            bola1.setVelActualX(10);
        }
        if (bola1.getVelActualY()>10){
            bola1.setVelActualY(10);
        }*/
        Log.d("billar",bola1.getVelActualX()+"-- velocidad --"+bola1.getVelActualY());
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        //Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        apunta=true;
        lineX2=event.getX();
        lineY2=event.getY();

    }


}
