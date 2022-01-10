package com.example.ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private MyView vw;
    // 정점 하나에 대한 정보를 가지는 클래스
    public class Vertex{
        Vertex(float ax, float ay,boolean ad){
            x = ax;
            y = ay;
            draw = ad;
        }
        float x;
        float y;
        boolean draw;
    }

    ArrayList<Vertex> arVertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vw = new MyView(this);
        setContentView(vw);

        arVertex = new ArrayList<Vertex>();
    }

    protected  class MyView extends View {
        Paint mPaint;

        public MyView(Context context){
            super(context);

            //Paint 객체 미리 초기화
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            mPaint.setAntiAlias(true);
        }

        public void onDraw(Canvas cancas){
            cancas.drawColor(Color.LTGRAY);

            //정점을 순화 하면서 선분으로 잇는다.
            for(int i = 0;i<arVertex.size();i++){
                if(arVertex.get(i).draw){
                    cancas.drawLine(arVertex.get(i-1).x,arVertex.get(i-1).y,arVertex.get(i).x,
                            arVertex.get(i).y,mPaint);
                }
            }
        }

        //터치 이동시마다 정점을 추가한다.
        public boolean onTouchEvent(MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                arVertex.add(new Vertex(event.getX(),event.getY(),false));
                return true;
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                arVertex.add(new Vertex(event.getX(), event.getY(),true));
                invalidate();
                return true;
            }
            return false;
        }
    }
}