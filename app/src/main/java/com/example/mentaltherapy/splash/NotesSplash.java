package com.example.mentaltherapy.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mentaltherapy.Notes;
import com.example.mentaltherapy.R;

public class NotesSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_splash);
        LottieAnimationView notes=findViewById(R.id.notesSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Notes.class));
                finish();
            }
        },4700);
    }
}