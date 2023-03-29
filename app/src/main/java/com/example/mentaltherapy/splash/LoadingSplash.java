package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.MainActivity;
import com.example.mentaltherapy.R;

public class LoadingSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_splash);
        TextView hi=findViewById(R.id.hi);
        LottieAnimationView hiAnimation=findViewById(R.id.loadingSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingSplash.this, MainActivity.class));
                finish();
            }
        },2000);
    }
}