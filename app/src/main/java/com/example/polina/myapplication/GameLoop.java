package com.example.polina.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameLoop extends Thread {
    public SurfaceHolder surfaceHolder;
    public Game game;
    private double averageFPS;
    public static Canvas canvas;

    public GameLoop(SurfaceHolder surfaceHolder, Game game){
        super();
        this.surfaceHolder=surfaceHolder;
        this.game=game;
    }
    @Override
    public void run(){

        long startTime;
        long timeMilis=1000/30;
        long waitTime;
        int frameCount=0;
        long totalTime=0;
        long targetTime=1000/30;
        while(true)
        {
            startTime=System.nanoTime();
            canvas=null;
            try{
                canvas=this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.game.update();
                    this.game.draw(canvas);
                }
            }catch (Exception e){e.printStackTrace();}

         finally {
                if(canvas!=null)
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }catch (Exception e){e.printStackTrace();}
            }
            timeMilis=(System.nanoTime()-startTime)/1000000;
            waitTime=targetTime-timeMilis;
            try{
                if(waitTime>0)
                    this.sleep(waitTime);
            }catch (Exception e){e.printStackTrace();}

            totalTime+=System.nanoTime()-startTime;
            frameCount++;
            if(frameCount==30){
                averageFPS=1000/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;

            }
        }



    }


}
