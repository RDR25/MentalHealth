package com.example.mentaltherapy.menu;

import static com.example.mentaltherapy.Login.USER;
import static com.example.mentaltherapy.Login.databaseReference;
import static com.example.mentaltherapy.menu.Meditation.SHARED_PREFS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mentaltherapy.menu.notes.Newnotes;
import com.example.mentaltherapy.menu.notes.NotesAdapter;
import com.example.mentaltherapy.menu.notes.NotesItems;
import com.example.mentaltherapy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {
    FloatingActionButton newNotes;
    RecyclerView notesRCV;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        newNotes=findViewById(R.id.addnote);
        notesRCV=findViewById(R.id.notesrcv);
        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String s=sharedPreferences.getString(USER,"");
        newNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notes.this, Newnotes.class));
            }
        });
        notesRCV.setLayoutManager(new LinearLayoutManager(this));
        List<NotesItems> notesItemsList=new ArrayList<>();
        if(true){
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(s)){
                       // Toast.makeText(Notes.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            for(DataSnapshot nT:snapshot1.child("notes").getChildren()){
                                //NotesItems notesItems= nT.getValue(NotesItems.class);
                              //  Toast.makeText(Notes.this, nT.getValue().toString(), Toast.LENGTH_SHORT).show();
                                if(snapshot1.getKey().equals(s)){
                                    Toast.makeText(Notes.this, "sd  "+snapshot1.getKey(), Toast.LENGTH_SHORT).show();
                                    notesItemsList.add(new NotesItems(nT.child("NotesTitle").getValue().toString(),nT.child("NotesDescription").getValue().toString()));
                                }
                               // Toast.makeText(Notes.this, nT.child("NotesDescription").getValue().toString(), Toast.LENGTH_SHORT).show();
                            }
                           //Toast.makeText(Notes.this, snapshot1.child("notes").getChildren()., Toast.LENGTH_LONG).show();
                           // Log.i("HJJ",snapshot1.getValue().toString());
                           //notesItemsList.add(new NotesItems(snapshot1.getValue().toString(),snapshot1.child("NotesDescription").getValue().toString()));
                        }

                        notesRCV.setAdapter(new NotesAdapter(getApplicationContext(),notesItemsList));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

           /* notesItemsList.add(new NotesItems("dss","Dfdg"));
            Toast.makeText(this, notesItemsList.get(0).getNotesDescription(), Toast.LENGTH_SHORT).show();
            */

        }



    }

}