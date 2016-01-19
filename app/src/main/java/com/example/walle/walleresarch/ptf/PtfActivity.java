package com.example.walle.walleresarch.ptf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.walle.walleresarch.R;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by walle on 2016/1/19.
 */
public class PtfActivity extends Activity{
    PtrFrameLayout ptr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptf);
        findView();
        init();
    }
    private void findView(){
    ptr= (PtrFrameLayout) findViewById(R.id.ptf);
    }
    private void  initList(){

    }
    private void init(){
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, 50, 0, 50);
        header.initWithString("Mars by crazybaby");

        ptr.setDurationToCloseHeader(5500);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
        ptr.DEBUG=true;
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr.refreshComplete();
                    }
                }, 5500);
            }
        });

    }

}
