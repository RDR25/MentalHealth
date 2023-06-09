package com.example.mentaltherapy.menu.notes;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;

public class NotesViewHolder extends RecyclerView.ViewHolder{
    TextView nTitle;
    TextView nDescription;
    TextView nTimeStamp;

    LinearLayout linearLayout;
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        nTitle=itemView.findViewById(R.id.notesTitle);
        nDescription=itemView.findViewById(R.id.notesDescription);
        nTimeStamp=itemView.findViewById(R.id.notesTimeStamp);
        linearLayout=itemView.findViewById(R.id.ll1);
    }
}
