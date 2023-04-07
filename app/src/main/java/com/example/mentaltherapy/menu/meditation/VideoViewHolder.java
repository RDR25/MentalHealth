package com.example.mentaltherapy.menu.meditation;

import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    VideoView videoView;
    TextView medTitle;
    TextView medDiscription;
    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        videoView=itemView.findViewById(R.id.med_video);
        medTitle=itemView.findViewById(R.id.med_title);
        medDiscription=itemView.findViewById(R.id.med_discription);
    }
}
