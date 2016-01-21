package com.example.walle.walleresarch.ptf.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

/**
 * Created by walle on 2016/1/20.
 */
public class TestHeader extends View implements PtrUIHandler {
        TestHeaderDrable testHeaderDrable;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;
    String TAG="TestHeader";
    public TestHeader(Context context) {
        super(context);
        init();
    }

    public TestHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        testHeaderDrable=new TestHeaderDrable(getContext(),this);
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = testHeaderDrable.getTotalDragDistance() * 5 / 4;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        testHeaderDrable.setBounds(pl, pt, pl + right - left, pt + bottom - top);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        testHeaderDrable.draw(canvas);
        float percent = mPtrTensionIndicator.getOverDragPercent();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.i(TAG,"onUIReset");
        testHeaderDrable.setuiState(0);
        testHeaderDrable.resetOriginals();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.i(TAG,"onUIRefreshPrepare");
        testHeaderDrable.setuiState(0);

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.i(TAG,"onUIRefreshBegin");
        testHeaderDrable.start();
        testHeaderDrable.setuiState(1);
        float percent = mPtrTensionIndicator.getOverDragPercent();
        testHeaderDrable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        testHeaderDrable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        Log.i(TAG,"onUIRefreshComplete");
        testHeaderDrable.setuiState(2);
        float percent = mPtrTensionIndicator.getOverDragPercent();
        testHeaderDrable.stop();
        testHeaderDrable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        testHeaderDrable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        Log.i(TAG,"onUIPositionChange");
        float percent = mPtrTensionIndicator.getOverDragPercent();
        testHeaderDrable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        testHeaderDrable.setPercent(percent);
        invalidate();
    }
    @Override
    public void invalidateDrawable(Drawable dr) {
        if (dr == testHeaderDrable) {
            invalidate();
        } else {
            super.invalidateDrawable(dr);
        }
    }
}
