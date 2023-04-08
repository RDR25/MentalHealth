package com.example.mentaltherapy;

import static com.example.mentaltherapy.menu.Meditation.SHARED_PREFS;
import static com.example.mentaltherapy.menu.Meditation.TEXT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentaltherapy.menu.MedCompleted;
import com.example.mentaltherapy.splash.ChallengeSplash;
import com.example.mentaltherapy.splash.ChatbotSplash;
import com.example.mentaltherapy.splash.EmergencySplash;
import com.example.mentaltherapy.splash.MeditationSplash;
import com.example.mentaltherapy.splash.MusicSplash;
import com.example.mentaltherapy.splash.NotesSplash;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SimpleDateFormat sdf;
    static String dateVal="null";
    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView meditation=findViewById(R.id.meditationbtn);
        CardView chatbot=findViewById(R.id.chatbotbtn);
        CardView music=findViewById(R.id.music);
        CardView notes=findViewById(R.id.notesbtn);
        CardView challenge=findViewById(R.id.challengebtn);
        CardView emergency=findViewById(R.id.emergencybtn);
        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                editor=sharedPreferences.edit();
                sdf=new SimpleDateFormat("dd.MM.yyyy");
                currentDate=sdf.format(new Date());
                Toast.makeText(MainActivity.this, currentDate+" "+dateVal, Toast.LENGTH_SHORT).show();
                if(dateVal.equals("null")) dateVal=currentDate;
                if(!(currentDate.equals(dateVal))){
                    editor.putString(TEXT,"incomplete");
                    dateVal=currentDate;
                }

                if(sharedPreferences.getString(TEXT,"").equals("complete")){
                    startActivity(new Intent(MainActivity.this, MedCompleted.class));
                }else{
                    startActivity(new Intent(MainActivity.this, MeditationSplash.class));
                }
            }
        });
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChatbotSplash.class));
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MusicSplash.class));
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesSplash.class));
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChallengeSplash.class));
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EmergencySplash.class));
            }
        });
    }
}