package com.example.mentaltherapy.menu;

import static com.example.mentaltherapy.menu.Meditation.SHARED_PREFS;
import static com.example.mentaltherapy.menu.Meditation.TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentaltherapy.MainActivity;
import com.example.mentaltherapy.R;

public class MedCompleted extends AppCompatActivity {
    Button rewatchBtn;
    Button menuBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_completed);
        rewatchBtn=findViewById(R.id.rewatchbtn);
        menuBtn=findViewById(R.id.menubtn);
        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        rewatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(TEXT,"incomplete");
                Toast.makeText(MedCompleted.this, sharedPreferences.getString(TEXT,""), Toast.LENGTH_SHORT).show();
                editor.apply();
                startActivity(new Intent(MedCompleted.this, Meditation.class));
                finish();
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(TEXT,"complete");
                Toast.makeText(MedCompleted.this, sharedPreferences.getString(TEXT,""), Toast.LENGTH_SHORT).show();
                editor.apply();
                startActivity(new Intent(MedCompleted.this, MainActivity.class));
                finish();
            }
        });
    }
}