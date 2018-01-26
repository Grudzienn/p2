package com.example.polina.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Display;
import android.view.WindowManager;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Display;
import android.view.WindowManager;


public class Assets {
    public static Bitmap strzalka,skok,tabliczka;
    public static Bitmap [] graczAnimations,ogien;
    public static int DISPLAY_WIDTH,DISPLAY_HEIGHT;



    public static void ini(){
        WindowManager wm = (WindowManager) Constants.CURRENT_CONTEXT.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DISPLAY_HEIGHT=display.getHeight();
        DISPLAY_WIDTH=display.getWidth();
        graczAnimations=new Bitmap[12];
        ogien=new Bitmap[2];
        tabliczka= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.tabliczka),(int)(display.getHeight()*15/100),display.getWidth()*30/100);
        strzalka= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.strzalka),(int)(display.getHeight()*25/100),display.getWidth()*40/100);
        skok= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.skok),(int)(display.getHeight()/2),display.getWidth()/5);
        ogien[0]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.ogien),(int)(display.getHeight()/4),display.getWidth()/6);
        ogien[1]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.ogien2),(int)(display.getHeight()/4),display.getWidth()/6);
        graczAnimations[0]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g1),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[1]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g2),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[2]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g3),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[3]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g4),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[4]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g5),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[5]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g6),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[6]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g7),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[7]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g8),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[8]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g9),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[9]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g10),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[10]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g11),(int)(display.getHeight()/2),display.getWidth()/5);
        graczAnimations[11]= getResizedBitmap(new BitmapFactory().decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.g12),(int)(display.getHeight()/2),display.getWidth()/5);




    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;



        Matrix matrix = new Matrix();


        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }
}
