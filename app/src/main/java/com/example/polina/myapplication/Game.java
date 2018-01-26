package com.example.polina.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Random;


public class Game extends SurfaceView implements SurfaceHolder.Callback {

    public GameLoop loop;
    public UIObject gracz;
    public UIObject strzalka,strzalka2;
    public UIObject ogien;
    public UIObject tabliczka;
    public long lastSkinChange,lastFireChange;
    public boolean czySkacze,czyPunktZostalPrzyznany;
    public int predkosc;
    int speed;
    public int punkty,najlepszy_wynik;

    public Game(Context context){
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT=context;
        Assets.ini();
        lastSkinChange=0;
        lastFireChange=0;

        tabliczka=new UIObject(Assets.tabliczka,Assets.DISPLAY_WIDTH*35/100,Assets.DISPLAY_HEIGHT*7/100);
        gracz=new UIObject(Assets.graczAnimations,Assets.DISPLAY_WIDTH*3/100,Assets.DISPLAY_HEIGHT*40/100);
        gracz.setBounds(Assets.DISPLAY_WIDTH*5/100,Assets.DISPLAY_HEIGHT*10/100,Assets.DISPLAY_WIDTH*15/100,Assets.DISPLAY_HEIGHT*45/100);
        gracz.image=Assets.skok;
        strzalka=new UIObject(Assets.strzalka,Assets.DISPLAY_WIDTH,Assets.DISPLAY_HEIGHT*75/100);
        strzalka2=new UIObject(Assets.strzalka,Assets.DISPLAY_WIDTH*170/100,Assets.DISPLAY_HEIGHT*75/100);
        ogien=new UIObject(Assets.ogien,Assets.DISPLAY_WIDTH,Assets.DISPLAY_HEIGHT*61/100);
        ogien.setBounds(Assets.DISPLAY_WIDTH*26/1000,Assets.DISPLAY_HEIGHT*9/100,Assets.DISPLAY_WIDTH*13/100,Assets.DISPLAY_HEIGHT*48/100);
        czyPunktZostalPrzyznany=false;
        czySkacze=false;
        predkosc=0;
        speed=3;
        punkty=0;
        najlepszy_wynik=0;


        setFocusable(true);
    }


    public boolean onTouchEvent(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!czySkacze){

                    czySkacze=true;
                    predkosc-=70;
                }
                break;
        }
        return true;
    }





    public void update(){
        if(System.currentTimeMillis()-lastSkinChange>35-speed*2){
            gracz.indexUpdate();
            lastSkinChange=System.currentTimeMillis();
        }
        if(System.currentTimeMillis()-lastFireChange>50){
            ogien.indexUpdate();
            lastFireChange=System.currentTimeMillis();
        }



        strzalka.x-=Assets.DISPLAY_WIDTH*speed/100;
        strzalka2.x-=Assets.DISPLAY_WIDTH*speed/100;
        ogien.x-=Assets.DISPLAY_WIDTH*speed/100;

        if(strzalka.x<0-strzalka.image.getWidth())
            strzalka.x=Assets.DISPLAY_WIDTH;
        if(strzalka2.x<0-strzalka2.image.getWidth())
            strzalka2.x=Assets.DISPLAY_WIDTH;
        if(ogien.x<0-ogien.images[ogien.frameIndex].getWidth()){
            ogien.x=Assets.DISPLAY_WIDTH*(100+(int)(Math.random()*((40-1)+1)  + 1))/100; //40 - max, 1 - min, + min
            czyPunktZostalPrzyznany=false;
        }

        if(czySkacze){
            gracz.y+=predkosc;
            predkosc+=10;
        }
        if(predkosc>70){
            predkosc=0;
            czySkacze=false;
        }


        if(punkty>najlepszy_wynik)
        najlepszy_wynik=punkty;

        if(punkty<10)
            speed=3;
        if(punkty>=10&&punkty<30)
            speed=4;
        if(punkty>=30&&punkty<50)
            speed=5;
        if(punkty>=50&&punkty<100)
            speed=6;
        if(punkty>=100&&punkty<200)
            speed=7;
        if(punkty>=200&&punkty<500)
            speed=8;
        if(punkty>=500&&punkty<2000)
            speed=9;
        if(punkty>=2000)
            speed=10;


        if(gracz.x+gracz.bleft>ogien.x+ogien.bright&&!czyPunktZostalPrzyznany) {
            punkty++;
            czyPunktZostalPrzyznany = true;
        }



        if(gracz.x+gracz.bright>ogien.x+ogien.bleft&&gracz.y+gracz.bbot>ogien.y+ogien.btop&&gracz.x+gracz.bleft<ogien.x+ogien.bright)
            punkty=0;



    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        canvas.drawColor(Color.rgb(0,146,62));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(Assets.DISPLAY_HEIGHT*10/100);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(String.valueOf(punkty),Assets.DISPLAY_WIDTH*5/100,Assets.DISPLAY_HEIGHT*10/100,paint);
        canvas.drawText(String.valueOf(najlepszy_wynik),Assets.DISPLAY_WIDTH*85/100,Assets.DISPLAY_HEIGHT*10/100,paint);

        tabliczka.draw(canvas);
        strzalka.draw(canvas);
        strzalka2.draw(canvas);


        if(czySkacze)
            gracz.draw(canvas);
        else    
            gracz.drawAnimation(canvas);

        ogien.drawAnimation(canvas);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        loop=new GameLoop(getHolder(),this);
        loop.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
