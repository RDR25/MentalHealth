package com.example.mentaltherapy.menu.meditation;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;
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

        try{
            holder.videoView.setVideoURI(Uri.parse("android.resource://com.example.mentaltherapy/"+videoItemsList.get(position).getVideourl()));
            Log.d(TAG,"RDRVV :"+ videoItemsList.get(position).getVideourl() );
            Toast.makeText(holder.videoView.getContext(), "df  "+videoItemsList.get(position).getVideourl(), Toast.LENGTH_LONG).show();
            MediaController mediaController =new MediaController(holder.videoView.getContext());
            holder.videoView.setMediaController(mediaController);
            mediaController.setAnchorView(holder.videoView);
        }catch (Exception e){
            Toast.makeText(holder.videoView.getContext(), "ds "+e, Toast.LENGTH_SHORT).show();
            Log.d(TAG,"RDRVV :"+ e);
        }


        holder.medTitle.setText(videoItemsList.get(position).getVideoTitle());
        holder.medDiscription.setText(videoItemsList.get(position).getVideoDiscription());
    }

    @Override
    public int getItemCount() {
        return videoItemsList.size();
    }
}
