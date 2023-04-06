package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.menu.Chatbot;
import com.example.mentaltherapy.R;

public class ChatbotSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot_splash);
        LottieAnimationView chatbotAnime=findViewById(R.id.chatbotSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Chatbot.class));
                finish();
            }
        },2300);
    }
}