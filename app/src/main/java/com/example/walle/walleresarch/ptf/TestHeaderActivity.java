package com.example.walle.walleresarch.ptf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.walle.walleresarch.R;
import com.example.walle.walleresarch.ptf.header.TestHeader;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by walle on 2016/1/19.
 */
public class TestHeaderActivity extends Activity{
    PtrFrameLayout ptr;
    String TAG="Test1HeaderActivity";
    private TextView toClassic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ptftestheader);
        findView();
        init();
    }
    private void findView(){
        ptr= (PtrFrameLayout) findViewById(R.id.ptf);
    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==toClassic.getId()){
                startActivity(new Intent(TestHeaderActivity.this,PtfClassicAcitivity.class));
            }
        }
    };

    private void init(){
        TestHeader header = new TestHeader(this);
//        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, 50, 0, 50);
//        header.initWithString("Mars by crazybaby");

//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setUp(ptr);

//        ptr.setDurationToCloseHeader(5500);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
        ptr.disableWhenHorizontalMove(false);

        ptr.DEBUG=true;
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                boolean checkContentCanBePulledDown=PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                Log.i(TAG,"checkCanDoRefresh"+checkContentCanBePulledDown);


                return checkContentCanBePulledDown;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i(TAG,"onRefreshBegin");

                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr.refreshComplete();
                    }
                }, 10000);
            }
        });

    }

}
