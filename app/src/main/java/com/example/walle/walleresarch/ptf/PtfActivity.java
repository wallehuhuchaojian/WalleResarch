package com.example.walle.walleresarch.ptf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.walle.walleresarch.R;

import java.util.logging.Logger;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by walle on 2016/1/19.
 */
public class PtfActivity extends Activity{
    PtrFrameLayout ptr;
    String TAG="PtfActivity";
    private TextView toClassic,toTestHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptf);
        findView();
        init();
    }
    private void findView(){
        toClassic= (TextView) findViewById(R.id.to_classic);
        toTestHeader= (TextView) findViewById(R.id.to_testheader);
        ptr= (PtrFrameLayout) findViewById(R.id.ptf);
        toClassic.setOnClickListener(onClickListener);
        toTestHeader.setOnClickListener(onClickListener);

    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==toClassic.getId()){
                startActivity(new Intent(PtfActivity.this,PtfClassicAcitivity.class));
            }else if (v.getId()==toTestHeader.getId()){
                startActivity(new Intent(PtfActivity.this,TestHeaderActivity.class));
            }
        }
    };

    private void init(){
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, 50, 0, 50);
        header.initWithString("Mars by crazybaby");

//        ptr.setDurationToCloseHeader(5500);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
        ptr.disableWhenHorizontalMove(true);

//        ptr.DEBUG=true;
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
                }, 2000);
            }
        });

    }

}
