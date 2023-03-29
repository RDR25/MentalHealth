package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.Meditation;
import com.example.mentaltherapy.R;

public class MeditationSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_splash);
        LottieAnimationView yoga=findViewById(R.id.yogaSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Meditation.class));
                finish();
            }
        },2500);
    }
}