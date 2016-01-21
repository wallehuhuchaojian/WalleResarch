package com.example.walle.walleresarch.ptf.header;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.example.walle.walleresarch.R;

import java.util.logging.Logger;

/**
 * Created by walle on 2016/1/20.
 */
public class TestHeaderDrable extends Drawable implements Animatable {
    private Context context;
    private View parent;
    private Matrix matrix;
    private int headerHight=200;
    private  int screenW,screenH;
    private Bitmap circleRight,circleLeft,circleRefresh,circleWave;
    private static final int ANIMATION_DURATION = 1000;
    private boolean isRefreshing=false;
    private Animation animation;
    private int leftSiz=50,rightSize=50;
    private float rightLeftOffset,rightTopOffset;
    private float leftLeftOffset,leftTopOffset;

    public TestHeaderDrable(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        matrix=new Matrix();
        initData();
        initDrawable();
        setupAnimations();
    }
    private Context getContext(){
        return context;
    }
    private void initData(){
        screenW = getContext().getResources().getDisplayMetrics().widthPixels;
        rightLeftOffset =  screenW;
        rightTopOffset = (headerHight * 1.5f);
        leftLeftOffset=0;
        leftTopOffset=(headerHight * 0f);;
    }
    private void initDrawable(){
        circleLeft= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.test1);
        circleLeft=Bitmap.createScaledBitmap(circleLeft, leftSiz, leftSiz, true);
        circleRight= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.test2);
        circleRight=Bitmap.createScaledBitmap(circleRight, rightSize, rightSize, true);
        circleRefresh=BitmapFactory.decodeResource(getContext().getResources(), R.drawable.test3);
        circleRefresh=Bitmap.createScaledBitmap(circleRefresh, leftSiz, leftSiz, true);
        circleWave=BitmapFactory.decodeResource(getContext().getResources(), R.drawable.iv_wave);
        circleWave=Bitmap.createScaledBitmap(circleWave, leftSiz, leftSiz, true);
    }
    private void setupAnimations() {
        animation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setRotate(interpolatedTime);
            }
        };
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(LINEAR_INTERPOLATOR);
        animation.setDuration(ANIMATION_DURATION);
    }
    private float rotate;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private  int top=0;
    private float percent=0.0f;
    public void setRotate(float rotate) {
        this.rotate = rotate;
        parent.invalidate();
        invalidateSelf();
    }
    public  void setPercent(float percent){
        this.percent=percent;
        setRotate(percent);
    }
    public void offsetTopAndBottom(int offset) {
        top = offset;
        invalidateSelf();
    }
    public void resetOriginals() {
        setPercent(0);
        setRotate(0);
    }
    @Override
    public void start() {
        animation.reset();
        isRefreshing = true;
        parent.startAnimation(animation);
    }
    int uistate;//0为复位状态，1开始刷新，2，刷新完成
    public void setuiState(int state){
        uistate=state;
    }

    @Override
    public void stop() {
        parent.clearAnimation();
        isRefreshing = false;
        resetOriginals();
    }


    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, top+headerHight);
    }

    @Override
    public void draw(Canvas canvas) {
        final int saveCount = canvas.save();
        canvas.translate(0, headerHight - top);
        drawLeftCirlce(canvas);
        drawRightCirlce(canvas);
        if (uistate==1){
            drawwave(canvas);
        }else {
            currentsize=50;
        }

        canvas.restoreToCount(saveCount);
    }

    private static final float left_grown = 1.2f;
    private static final float right_grown = 1.5f;
    private int ratedegress=10;
    private void drawLeftCirlce(Canvas canvas){
        Matrix matrix=this.matrix;
        matrix.reset();
        float dragPercent = percent;
        if (dragPercent > 1.0f) { // Slow down if pulling over set height
//            dragPercent = (dragPercent + 9.0f) / 10;
            dragPercent = 1f;
        }
        float sunRadius = (float) rightSize / 2.0f;

        float offsetX = leftLeftOffset+screenW/2*(dragPercent)-leftSiz/2;
        float offsetY=leftTopOffset+headerHight*(dragPercent);
        Log.i("drawLeftCirlce","offsetX"+offsetX+"offsetY===="+offsetY);
        matrix.preTranslate(offsetX, offsetY);
        float r = (isRefreshing ? -360 : 360) * rotate ;
//        matrix.preRotate(r, offsetX, offsetY);

        canvas.drawBitmap(circleLeft, matrix, null);
    }





    private void drawRightCirlce(Canvas canvas){
        Matrix matrix=this.matrix;
        matrix.reset();
        float dragPercent = percent;
        Bitmap drawCircle;
        if (dragPercent > 1.0f) { // Slow down if pulling over set height
//            dragPercent = (dragPercent + 9.0f) / 10;
            dragPercent = 1f;
        }
        if (dragPercent>0.95){
            drawCircle=circleRefresh;
        }else {
            drawCircle=circleRight;
        }
        float offsetX = rightLeftOffset-screenW/2*(dragPercent)-leftSiz/2;
        float offsetY=rightTopOffset-headerHight/2*(dragPercent);
        Log.i("drawLeftCirlce", "offsetX" + offsetX + "offsetY====" + offsetY);
        matrix.preTranslate(offsetX, offsetY);
//       if (isRefreshing){
//           drawCircle=circleRefresh;
//       }else {
//           drawCircle=circleRight;
//       }
//        matrix.preRotate(r, offsetX, offsetY);

        canvas.drawBitmap(drawCircle, matrix, null);

    }
    int scaleGroth=leftSiz/10;
    int currentsize=leftSiz;
    Paint paint;
    private void drawwave(Canvas canvas){
        if (paint==null){
            paint=new Paint();
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(255);
            paint.setAntiAlias(true);
        }
        if (currentsize<leftSiz){
            currentsize=leftSiz;
        }
        if (currentsize<screenW){
            currentsize=currentsize+scaleGroth;
            paint.setAlpha((int) (255-255*(float)(currentsize)/(float)(screenW)));

        }else {
            currentsize=leftSiz;
            paint.setAlpha(0);
        }
        canvas.drawCircle(screenW/2,headerHight+leftSiz/2,currentsize/2,paint);
//        Matrix matrix=this.matrix;
//        matrix.reset();
//
//        matrix.setTranslate(screenW / 8, headerHight);
//        matrix.preScale((float)(currentsize)/(float)(leftSiz), (float)(currentsize)/(float)(leftSiz));
//        //,screenW / 8+leftSiz/2,headerHight+leftSiz/2
//
//        canvas.drawBitmap(circleWave, matrix, null);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
    public int getTotalDragDistance() {
        return headerHight;
    }
}
