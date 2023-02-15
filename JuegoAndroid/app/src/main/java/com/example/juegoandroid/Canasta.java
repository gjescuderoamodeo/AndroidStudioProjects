package com.example.juegoandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.juegoandroid.sprites.Bola;
import com.example.juegoandroid.sprites.Sprite;

public class Canasta extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1,bola2,bola3;

    float lineX1,lineY1,lineX2,lineY2;
    boolean estaDentro=false;
    boolean apunta=false;



    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;


    public Canasta(Context context, int x, int y) {
        super(context,x,y);

        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {

        //Blanca
        bola1 = new Bola(this,this.mScreenX/2, this.mScreenY-300, 50,Color.WHITE);
        actores.add(bola1);  bola1.setup();
        bola2 = new Bola(this,this.mScreenX/2, 400, 70,Color.RED);
        actores.add(bola2);  bola2.setup();
        bola2.setVelActualX(50);

        bola3 = new Bola(this,this.mScreenX/2, 200, 70,Color.BLACK);
        actores.add(bola3);  bola3.setup();
        //bola3.setVelActualX(100);
    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //Log.d("colision", String.valueOf(this.bola1.puntuacion));
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

        //imagen cancha baloncesto
        // Cargar la imagen en un objeto Bitmap
        paint.setAlpha(128);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cancha);

        // Dibujar el Bitmap en el Canvas
        //canvas.drawBitmap(bitmap, 0, 0, paint);

        // Cargar la imagen de un recurso
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cancha);

        // Obtener las dimensiones de la pantalla
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Calcular la relación de aspecto de la imagen
        float bitmapRatio = (float)bitmap.getWidth() / (float)bitmap.getHeight();
        float screenRatio = (float)screenWidth / (float)screenHeight;

        // Calcular las dimensiones de la imagen escalada
        int scaledWidth, scaledHeight;
        if (screenRatio > bitmapRatio) {
            scaledWidth = screenWidth;
            scaledHeight = (int)(scaledWidth / bitmapRatio);
        } else {
            scaledHeight = screenHeight;
            scaledWidth = (int)(scaledHeight * bitmapRatio);
        }

        // Escalar la imagen a las nuevas dimensiones
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);

        // Dibujar la imagen en el canvas
        canvas.drawBitmap(scaledBitmap, 0, 0, null);

        // Liberar la memoria de los bitmaps
        bitmap.recycle();
        scaledBitmap.recycle();


        synchronized(actores) {
            for (Sprite actor : actores) {
                    actor.pinta(canvas);
            }
        }

        //dibujamos puntuacion
        paint.setTextSize(100);
        canvas.drawText("JUEGO CANASTA: ", 100, 100, paint);
        canvas.drawText("CANASTAS: "+this.bola1.puntuacion, 100, 500, paint);
        if(estaDentro){
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(5);
            canvas.drawLine(bola1.centroX,bola1.centroY,lineX2,lineY2,paint);
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

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        if(estaDentro){
            lineX2=event.getX();
            lineY2=event.getY();
            bola1.setVelActualX((lineX1-lineX2)/5);
            bola1.setVelActualY((lineY1-lineY2)/5);
            estaDentro=false;
            apunta=false;
        }
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        apunta=true;
        lineX2=event.getX();
        lineY2=event.getY();

    }


}
