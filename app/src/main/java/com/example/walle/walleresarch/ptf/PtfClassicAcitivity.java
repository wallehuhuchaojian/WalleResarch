package com.example.walle.walleresarch.ptf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.walle.walleresarch.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by walle on 2016/1/20.
 */
public class PtfClassicAcitivity extends Activity {
    PtrClassicFrameLayout ptf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptfclassic);
        findView();
        init();
    }
    private void findView(){
        ptf= (PtrClassicFrameLayout) findViewById(R.id.ptf);

    }
    private void init(){
        ptf.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptf.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptf.refreshComplete();
                    }
                }, 2000);
            }
        });
    }
}
