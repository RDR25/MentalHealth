package com.example.mentaltherapy.menu.meditation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;
import com.example.mentaltherapy.VideoPlayer;
import com.example.mentaltherapy.menu.Meditation;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    List<VideoItems> videoItemsList;
    String TAG="aasyn";
    public VideoAdapter(Context applicationContext, List<VideoItems> videoItemsList) {
        this.videoItemsList = videoItemsList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.imageView.setImageResource(videoItemsList.get(position).imgurl);
        holder.medTitle.setText(videoItemsList.get(position).getVideoTitle());
        holder.medDiscription.setText(videoItemsList.get(position).getVideoDiscription());
        int i=position;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.cardView.getContext(), VideoPlayer.class);
                Toast.makeText(view.getContext(), ""+videoItemsList.get(i).getVideourl(), Toast.LENGTH_SHORT).show();
                intent.putExtra("videoId",videoItemsList.get(i).getVideourl());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoItemsList.size();
    }
}
