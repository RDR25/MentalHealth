package com.example.mentaltherapy.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mentaltherapy.R;
import com.example.mentaltherapy.menu.meditation.VideoAdapter;
import com.example.mentaltherapy.menu.meditation.VideoItems;

import java.util.ArrayList;
import java.util.List;

public class Meditation extends AppCompatActivity {
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";
    Button completeBtn;
    Button rewatchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        completeBtn=findViewById(R.id.medCompleteBtn);

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(TEXT,"completed");
                editor.apply();
                startActivity(new Intent(Meditation.this, MedCompleted.class));
                finish();
            }
        });
        List<VideoItems> videoItemsList=new ArrayList<VideoItems>();
        videoItemsList.add(new VideoItems(R.raw.demo_video,R.drawable.yogaimg,"RDR","This is a demo"));
        videoItemsList.add(new VideoItems(R.raw.videoplayback,R.drawable.yogaimg,"RDR","This is a demo"));
        videoItemsList.add(new VideoItems(R.raw.demo_video,R.drawable.yogaimg,"RDR","This is a demo"));
        RecyclerView recyclerView=findViewById(R.id.rcMedVideo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoAdapter(getApplicationContext(),videoItemsList));
    }
}