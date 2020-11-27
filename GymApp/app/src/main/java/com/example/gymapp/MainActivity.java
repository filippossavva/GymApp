package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    //Constant
    public static int SPLASH_SCREEN =5000;
    public static String SERVICE = "0";
    //Variables
    MediaPlayer song;
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Read variables
        image = findViewById(R.id.image_logo);
        logo = findViewById(R.id.logo_name);
        slogan = findViewById(R.id.slogan);
        song = MediaPlayer.create(this,R.raw.welcome);
        song.start();
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inS = new Intent(getApplicationContext(), LogInHint.class);
                startService(inS);
                Intent in = new Intent(MainActivity.this, LoginActivity.class);
                in.putExtra(SERVICE, "1");
                startActivity(in);
                finish();
            }
        },SPLASH_SCREEN);
    }
}