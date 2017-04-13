package com.example.androiddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by hu on 17-4-13.
 */

public class BezierView extends View {
    private  int maxH;
    private int[] points;
    private int width,height;
    Paint paint;
    private PointVector[] p={new PointVector(0,0),new PointVector(0,0),new PointVector(0,0),new PointVector(0,0),new PointVector(0,0)};
    public BezierView(Context context, int high) {
        super(context);
        this.maxH = high;
        paint=new Paint();
        paint.setColor(0xffffffff);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    public BezierView(Context context) {
        super(context);
    }

    public void setPoints(int[] points) {
        this.points = points;
        initData();
        invalidate();
    }
    private void initData(){
        int num=points.length;
        int per=width/num;
        for (int i = 0; i < points.length; i++) {
            p[i].setX((i+1)*per);
            int h=points[i]*height/maxH;
            p[i].setY(h);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth();
        height=getHeight();
        if (points==null)
            return;
        lines(canvas);
        drawPoint(canvas);
        drawBezier(canvas);

    }
    private void lines(Canvas canvas){
        int num=points.length;
        int per=width/num;
        for (int i = 0; i < num; i++) {
            canvas.drawLine((i+1)*per,0,(i+1)*per,height,paint);
        }
    }
    private void drawPoint(Canvas canvas) {
        for (int i = 0; i < p.length; i++) {
            canvas.drawCircle(p[i].getX(),p[i].getY(),width/100,paint);
        }
    }
    private void drawBezier(Canvas canvas){
        Path path=new Path();
        int per=width/points.length;
        path.moveTo(p[0].getX(),p[0].getY());
//        path.cubicTo((p[0].getX()+p[1].getX())/2,p[1].getY(),(p[2].getX()+p[1].getX())/2,p[1].getY(),p[1].getX(),p[1].getY());
//        path.cubicTo((p[1].getX()+p[2].getX())/2,p[2].getY(),(p[3].getX()+p[2].getX())/2,p[2].getY(),p[2].getX(),p[2].getY());
//        path.cubicTo((p[2].getX()+p[3].getX())/2,p[3].getY(),(p[4].getX()+p[3].getX())/2,p[3].getY(),p[3].getX(),p[3].getY());

//        path.quadTo(p[0].getX(),p[0].getY(),p[1].getX(),p[1].getY());
//        path.quadTo(p[1].getX(),p[1].getY(),p[2].getX(),p[2].getY());
//        path.quadTo(p[2].getX(),p[2].getY(),p[3].getX(),p[3].getY());

//        path.quadTo((p[0].getX()+p[1].getX())/2,p[0].getY(),p[1].getX(),p[1].getY());
//        path.quadTo((p[1].getX()+p[2].getX())/2,p[1].getY(),p[2].getX(),p[2].getY());
//        path.quadTo((p[2].getX()+p[3].getX())/2,p[2].getY(),p[3].getX(),p[3].getY());
//        path.quadTo((p[3].getX()+p[4].getX())/2,p[3].getY(),p[4].getX(),p[4].getY());
        path.cubicTo((p[0].getX()+p[1].getX())/2,p[0].getY(),(p[0].getX()+p[1].getX())/2,p[1].getY(),p[1].getX(),p[1].getY());
        path.cubicTo((p[1].getX()+p[2].getX())/2,p[1].getY(),(p[1].getX()+p[2].getX())/2,p[2].getY(),p[2].getX(),p[2].getY());
        path.cubicTo((p[2].getX()+p[3].getX())/2,p[2].getY(),(p[2].getX()+p[3].getX())/2,p[3].getY(),p[3].getX(),p[3].getY());
        path.cubicTo((p[3].getX()+p[4].getX())/2,p[3].getY(),(p[3].getX()+p[4].getX())/2,p[4].getY(),p[4].getX(),p[4].getY());
        canvas.drawCircle((p[0].getX()+p[1].getX())/2,p[0].getY(),width/100,paint);
        canvas.drawCircle((p[1].getX()+p[2].getX())/2,p[0].getY(),width/100,paint);
        canvas.drawCircle((p[2].getX()+p[3].getX())/2,p[0].getY(),width/100,paint);
        canvas.drawCircle((p[3].getX()+p[4].getX())/2,p[0].getY(),width/100,paint);
        canvas.drawPath(path,paint);
//        path.q

    }
}
