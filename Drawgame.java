package com.example.user.example2d;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.example2d.R;

import java.lang.reflect.Array;
import java.util.Random;

import static android.graphics.RectF.intersects;

public class Drawgame extends View {
    private int width, height;
    private int posX=0,posY=0;
    Paint redpaint = new Paint();
    int posX1[]= new int[50];
    int posY1[]=new int[50];

    String TAG="Drawgame";
    Context mContext;
    Bitmap car;
    final MediaPlayer over;
    int counter=0;
    Random r = new Random();
    int i1 = r.nextInt(700 - 50) ;
    int i2=r.nextInt(500 - 50);
    int a=i1;
    int b=i2;
    int flagl=0,flagr=0,flagu=0,flagd=0;
    int baal=0;
    public Drawgame(Context context) {

        super(context);
        over=MediaPlayer.create(context,R.raw.over);

    }

    // Record
    // getter and setter method for currX and currY.


    @Override
    protected void onDraw(Canvas canvas) {
        if(flagl==1&&flagr==0&&flagu==0&&flagd==0){moveCarLeft();}
        if(flagl==0&&flagr==1&&flagu==0&&flagd==0){moveCarRight();}
        if(flagl==0&&flagr==0&&flagu==1&&flagd==0){moveCarUp();}
        if(flagl==0&&flagr==0&&flagu==0&&flagd==1){moveCarDown();}
        redpaint.setColor(Color.RED);
        RectF carrect = new RectF(posX,posY,posX+50,posY+50);
        RectF circlerect=new RectF((a-35),(b-35),(a+35),(b+35));
        if(	intersects(carrect, circlerect)){
            Random r = new Random();
            i1 = r.nextInt(width - 50) ;
            i2=r.nextInt(height - 50);
            a= i1;
            b=i2;
            counter++;

            //customViewActivity.updatetext();
        }

        super.onDraw(canvas);
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
        }baal=0;
        car=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.car),50,50,false);

        for(int y=counter;y>=1;y--){
            posX1[y]=posX1[y-1];
            posY1[y]=posY1[y-1];

            RectF carrect1= new RectF(posX1[y],posY1[y],posX1[y]+5,posY1[y]+1);
            canvas.drawBitmap(car,posX1[y],posY1[y],null);
            if((y>5)&&intersects(carrect1,carrect)){
                baal++;
            }}
        if(baal>=3)
        {

            redpaint.setTextSize(100);
            canvas.drawText("Gameover "+counter,10,500,redpaint);
            over.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            invalidate();
        baal=0;
        posX=0;
        posY=0;
        flagd=0;
        flagl=0;
        flagu=0;
        flagr=0;
          posX1= new int[50];
             posY1=new int[50];
             counter=0;
        }



        canvas.drawCircle(a,b, 35, redpaint);
        posX1[0]=posX;
        posY1[0]=posY;
        canvas.drawBitmap(car,posX,posY,null);


        redpaint.setTextSize(30);
        canvas.drawText("Score: "+counter,450,800,redpaint);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;


    }

    public void moveCarLeft() {
        flagl=1;
        flagr=0;flagu=0;flagd=0;
        if(posX==-50){posX=width+50;}


        posX=posX-10;
    }

    public void moveCarRight() {
        flagl=0;
        flagr=1;flagu=0;flagd=0;
        if(posX==width+50){posX=-50;}
        posX=posX+10;
    }


    public void moveCarUp() {

        if(posY==-50){posY=height+50;}
        posY=posY-10;
        flagl=0;
        flagr=0;flagd=0;flagu=1;
    }

    public void moveCarDown() {
        if(posY==height+50){posY=-50;}
        posY=posY+10;
        flagl=0;
        flagr=0;flagu=0;flagd=1;
    }
}