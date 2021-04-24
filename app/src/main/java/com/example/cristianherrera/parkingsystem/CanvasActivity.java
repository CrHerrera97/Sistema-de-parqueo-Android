package com.example.cristianherrera.parkingsystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_canvas);
        SpecialView myView = new SpecialView(this);
        setContentView(myView);
    }

    private class SpecialView extends View {
        float x = 50;
        float y = 50;

        String texto = "Evento";
        public  SpecialView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            canvas.drawCircle(x,y,30,paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(35);
            canvas.drawText(texto,100,130,paint);
            canvas.drawText("x="+x,100,50,paint);
            canvas.drawText("y="+y,100,90,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent evento){
            if (evento.getAction()==MotionEvent.ACTION_DOWN){
                texto="Action down";
                x=evento.getX();
                y=evento.getY();
                invalidate();
            }
            if (evento.getAction()==MotionEvent.ACTION_UP){
                texto="Action Up";
                x=evento.getX();
                y=evento.getY();
                invalidate();
            }
            if (evento.getAction()==MotionEvent.ACTION_MOVE){
                texto="Action move";
                x=evento.getX();
                y=evento.getY();
                invalidate();
            }
            return true;
        }
    }
}