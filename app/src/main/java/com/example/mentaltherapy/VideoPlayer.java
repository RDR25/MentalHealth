package com.example.mentaltherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mentaltherapy.menu.Meditation;

public class VideoPlayer extends AppCompatActivity {
    VideoView videoView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView=findViewById(R.id.videoLayout);
        btn=findViewById(R.id.goback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VideoPlayer.this, Meditation.class));
            }
        });
        Intent intent=getIntent();
        int videoId=intent.getIntExtra("videoId",0);
        videoView.setVideoURI(Uri.parse("android.resource://com.example.mentaltherapy/"+videoId));
        MediaController mediaController=new MediaController(videoView.getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}