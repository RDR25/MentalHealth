package com.example.mentaltherapy.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.example.mentaltherapy.R;
import com.example.mentaltherapy.menu.meditation.VideoAdapter;
import com.example.mentaltherapy.menu.meditation.VideoItems;

import java.util.ArrayList;
import java.util.List;

public class Meditation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        List<VideoItems> videoItemsList=new ArrayList<VideoItems>();
        videoItemsList.add(new VideoItems(R.raw.demo_video,"RDR","This is a demo"));
        videoItemsList.add(new VideoItems(R.raw.demo_video,"RDR","This is a demo"));
        videoItemsList.add(new VideoItems(R.raw.demo_video,"RDR","This is a demo"));
        RecyclerView recyclerView=findViewById(R.id.rcMedVideo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoAdapter(getApplicationContext(),videoItemsList));
    }
}