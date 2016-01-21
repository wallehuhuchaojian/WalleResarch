package com.example.walle.walleresarch.vector;

import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.walle.walleresarch.R;

/**
 * Created by walle on 2016/1/21.
 */
public class VectorTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vectortest);
        ImageView imageView= (ImageView) findViewById(R.id.image_vector);
        AnimatedVectorDrawable animatedVectorDrawable= (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.heart_vector_animator);
        imageView.setImageDrawable(animatedVectorDrawable);
        if (animatedVectorDrawable!=null){
            animatedVectorDrawable.start();
        }
    }
}
