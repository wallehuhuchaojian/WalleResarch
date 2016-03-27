package com.example.walle.walleresarch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.walle.walleresarch.audio.AudioActivity;
import com.example.walle.walleresarch.audio.AudioMyActivity;
import com.example.walle.walleresarch.audio.AudioTestActivity;
import com.example.walle.walleresarch.datapicker.DatePickerActivity;
import com.example.walle.walleresarch.draw.DrawableActivity;
import com.example.walle.walleresarch.draw.WaveActivity;
import com.example.walle.walleresarch.music.MusicTest;
import com.example.walle.walleresarch.ptf.PtfActivity;
import com.example.walle.walleresarch.remoteController.MusicActivity;
import com.example.walle.walleresarch.vector.VectorTestActivity;

public class MainActivity extends Activity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startActivity(new Intent(MainActivity.this, MusicActivity.class));

    }


}
