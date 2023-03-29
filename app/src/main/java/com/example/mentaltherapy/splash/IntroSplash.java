package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.Login;
import com.example.mentaltherapy.R;

public class IntroSplash extends AppCompatActivity {
    TextView title;
    LottieAnimationView mentalThearpy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash);
        title=findViewById(R.id.title);
        mentalThearpy=findViewById(R.id.introSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        },5000);
    }
}