package com.example.mentaltherapy.menu.meditation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView medTitle;
    TextView medDiscription;
    CardView cardView;
    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView=itemView.findViewById(R.id.cardVideoId);
        imageView=itemView.findViewById(R.id.med_videoImg);
        medTitle=itemView.findViewById(R.id.med_title);
        medDiscription=itemView.findViewById(R.id.med_discription);
    }
}
