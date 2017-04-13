package com.example.androiddraw;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends Activity {
    private int[] points=new int[5];
    private int max=32;
    private BezierView bezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout= (FrameLayout) findViewById(R.id.fl);

        bezierView=new BezierView(this,max);
        frameLayout.addView(bezierView);
        handler.sendEmptyMessageDelayed(1,1000);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setValue();
            handler.sendEmptyMessageDelayed(1,5000);
        }
    };
    private void setValue(){
        for (int i = 0; i < points.length; i++) {
            points[i]= (int) (Math.random()*max);
        }

        bezierView.setPoints(points);
    }
}
