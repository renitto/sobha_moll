package com.example.renitto.scmapp.Presenter;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renitto.scmapp.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

/**
 * Created by Renitto on 3/10/2016.
 */
public class ActSplash extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);



        TextView TV_header_splash = (TextView) findViewById(R.id.splash_text_main);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Ailerons.otf");
        TV_header_splash.setTypeface(tf, Typeface.NORMAL);







        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2s = 2000ms


                Intent intent = new Intent(ActSplash.this, ActivityGetemail.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();


            }
        }, 2000);



    }
}