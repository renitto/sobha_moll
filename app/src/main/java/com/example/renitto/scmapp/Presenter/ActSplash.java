package com.example.renitto.scmapp.Presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renitto.scmapp.R;
import com.github.florent37.viewanimator.ViewAnimator;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.splunk.mint.Mint;

import java.util.ArrayList;

/**
 * Created by Renitto on 3/10/2016.
 */
public class ActSplash extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);



        setContentView(R.layout.splashscreen);


        // setting shared preference
        int firsttime = 1;
        sharedPreferences = getSharedPreferences("FirstTime", Context.MODE_PRIVATE);
        final int checkvalue = sharedPreferences.getInt("firsttime",0);


        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putInt("firsttime",firsttime);
        editor.commit();



        TextView TV_header_splash = (TextView) findViewById(R.id.splash_text_main);
        ImageView Splash_iv_image = (ImageView)findViewById(R.id.splash_iv_image);
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Ailerons.otf");
//        TV_header_splash.setTypeface(tf, Typeface.NORMAL);



        TV_header_splash.setVisibility(View.GONE);


        ViewAnimator
                .animate(Splash_iv_image)
                .translationY(-1000, 0)
                .alpha(0,1)
                .andAnimate(TV_header_splash)
                .dp().translationX(-20, 0)
                .descelerate()
                .duration(2000)

                .thenAnimate(Splash_iv_image)
                .scale(1f,0.5f,1f)
                .accelerate()
                .duration(1000)

                .start();





        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2s = 2000ms


                if (checkvalue == 2) {
                    Intent intent = new Intent(ActSplash.this, ActivityHome.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(ActSplash.this, ActivityGetemail.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }




            }
        }, 3000);



    }
}