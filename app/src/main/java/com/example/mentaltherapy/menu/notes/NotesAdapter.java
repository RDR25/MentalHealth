package com.example.mentaltherapy.menu.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentaltherapy.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    List<NotesItems> notesData;

    public NotesAdapter(Context applicationContext, List<NotesItems> notesData) {
        this.notesData = notesData;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notelayout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.nTitle.setText(notesData.get(position).getNotesTitle());
        holder.nDescription.setText(notesData.get(position).getNotesDescription());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),Newnotes.class);
                intent.putExtra("nTitle",holder.nTitle.getText());
                intent.putExtra("nDescription",holder.nDescription.getText());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesData.size();
    }
}
