package com.example.videojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.videojuego.sprites.Bola;

import com.example.videojuego.sprites.Bujero;
import com.example.videojuego.sprites.Pala;
import com.example.videojuego.sprites.Sprite;
import com.example.videojuego.sprites.Sprite2;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1,bola2, bola3, bola4, bola5, bola6, bola7, bola8, bola9, bola10, bola11, bola12
            , bola13, bola14, bola15;
    Bujero bujero1,bujero2,bujero3,bujero4;
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

        bola3 = new Bola(this, 800, 300, 50,Color.BLUE);
        actores.add(bola3);  bola3.setup();
        bola11 = new Bola(this, 1000, 350, 50,Color.GRAY);
        actores.add(bola11);  bola11.setup();
        bola2 = new Bola(this, 800, 500, 50,Color.RED);
        actores.add(bola2);  bola2.setup();
        bola10 = new Bola(this, 1000, 500, 50,Color.MAGENTA);
        actores.add(bola10);  bola10.setup();
        bola12 = new Bola(this, 1200, 500, 50,Color.CYAN);
        actores.add(bola12);  bola12.setup();
        bola4 = new Bola(this, 800, 700, 50,Color.YELLOW);
        actores.add(bola4);  bola4.setup();
        bola6= new Bola(this, 1000,650, 50,Color.GREEN);
        actores.add(bola6);  bola6.setup();

        //pared.getmScreenX()
        //pared1 = new Pala(mScreenX, mScreenY);
        pared2 = new Pala(this, 0, 0, this.mScreenX, 0,this.mScreenX-50,this.mScreenY);
        actores.add(pared2);  pared2.setup();
        //canvas.drawRect(80, 0, 0, 1200, paint);
        pared1 = new Pala(this, 0, 0, 50, 0,0,this.mScreenY);
        actores.add(pared1);  pared1.setup();
        pared3 = new Pala(this, 0, 0, 20, 0,this.mScreenX,40);
        actores.add(pared3);  pared3.setup();
        pared4 = new Pala(this, 0, 0, 20, this.mScreenY,this.mScreenX,this.mScreenY-40);
        actores.add(pared4);  pared4.setup();
        //Log.d("pepe",String.valueOf(mScreenX));
        //actores2.add(pared1);  pared1.setup();


        //bujeros
        //bola5 = new Bola(this, 20, 20, 50,Color.BLACK);
        //actores.add(bola5);  bola5.setup();
        bola5 = new Bola(this, this.mScreenX-40, 40, 80,Color.BLACK);
        actores.add(bola5);  bola5.setup();
        bola6 = new Bola(this, 40, this.mScreenY-40, 80,Color.BLACK);
        actores.add(bola6);  bola6.setup();
        bola7 = new Bola(this, 40, 70, 70,Color.BLACK);
        actores.add(bola7);  bola7.setup();
        bola8 = new Bola(this, this.mScreenX-40, this.mScreenY-40, 80,Color.BLACK);
        actores.add(bola8);  bola8.setup();

        bola9 = new Bola(this, (this.mScreenX-40)/2, this.mScreenY-20, 70,Color.BLACK);
        actores.add(bola9);  bola9.setup();
        bola9 = new Bola(this, (this.mScreenX-40)/2, 20, 70,Color.BLACK);
        actores.add(bola9);  bola9.setup();




        //pared1 = new Pala(200,200);


    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if(actor.isVisible())
               actor.update();
        }

        for (Sprite2 actor : actores2) {
            if(actor.isVisible())
                actor.update();
        }
    }

    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        canvas.drawColor(Color.argb(255, 0, 102, 51));
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
