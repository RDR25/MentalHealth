package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.menu.Emergency;
import com.example.mentaltherapy.R;

public class EmergencySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_splash);
        LottieAnimationView emergency=findViewById(R.id.emergencySplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Emergency.class));
                finish();
            }
        },1000);
    }
}