package com.example.polina.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class UIObject {
    public Bitmap image;
    public Bitmap images[];
    public int frameIndex;
    public int x,y;
    public int bleft,btop,bright,bbot;

    public UIObject(Bitmap image,int x,int y){
        this.x=x;
        this.y=y;
        bleft=btop=bright=bbot=0;
        this.image=image;
    }
    public UIObject(Bitmap [] images,int x,int y){
        this.x=x;
        this.y=y;
        bleft=btop=bright=bbot=0;
        this.images=images;
        this.frameIndex=0;
    }

    public void indexUpdate(){
        frameIndex+=1;
        if(frameIndex>=images.length)
            frameIndex=0;
    }

    public void setBounds(int bleft,int btop,int bright,int bbot){
        this.bleft=bleft;
        this.btop=btop;
        this.bright=bright;
        this.bbot=bbot;
    }

    public void drawAnimation(Canvas canvas){
        canvas.drawBitmap(images[frameIndex],x,y,null);
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }

}