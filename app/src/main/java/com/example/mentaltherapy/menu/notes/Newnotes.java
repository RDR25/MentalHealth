package com.example.mentaltherapy.menu.notes;

import static com.example.mentaltherapy.Login.USER;

import static com.example.mentaltherapy.Login.databaseReference;
import static com.example.mentaltherapy.Login.firebaseDatabase;
import static com.example.mentaltherapy.menu.Meditation.SHARED_PREFS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentaltherapy.R;
import com.example.mentaltherapy.menu.Notes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Newnotes extends AppCompatActivity {
    EditText notesTitle;
    EditText notesDiscription;
    Button notesSave;
    Button notesDelete;
    SharedPreferences sharedPreferences;
    String userMobile;
    Calendar calendar;
    String timeStamp;
    SimpleDateFormat simpleDateFormat;
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnotes);
        notesTitle=findViewById(R.id.newnoteTitle);
        notesDiscription=findViewById(R.id.newnoteDescription);
        notesSave=findViewById(R.id.notesSave);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd.MM.yyyyHH:mm:ss");
        timeStamp=simpleDateFormat.format(calendar.getTime()).toString();
        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        userMobile=sharedPreferences.getString(USER,"");
        notesDelete=findViewById(R.id.notesDelete);

        notesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notesTitle.getText().toString()!=null || notesDiscription.getText().toString()!=null){

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                              //  if(snapshot1.child("notes").child())
                                for(DataSnapshot del:snapshot1.child("notes").getChildren()){
                                    if(del.child("NotesTitle").getValue().toString().equals(notesTitle.getText().toString())){
                                        String s=notesTitle.getText().toString();
                                        databaseReference.child("users").child(userMobile).child("notes").child(s).removeValue();
                                        Toast.makeText(Newnotes.this, s, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Newnotes.this,Notes.class));
                                        finish();
                                    }
                                   // Toast.makeText(Newnotes.this, del.child("NotesTitle").getValue().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        String nT=notesTitle.getText().toString();
        String unq=nT+";;"+userMobile+":";
        Intent intent=getIntent();
        String nTitle=intent.getStringExtra("nTitle");
        String nDescription=intent.getStringExtra("nDescription");
        if(nTitle!=null && nDescription!=null){
            notesTitle.setText(nTitle);
            notesDiscription.setText(nDescription);
        }
        notesSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pk=userMobile+":"+timeStamp+":"+notesTitle.getText().toString();
                Toast.makeText(Newnotes.this, timeStamp, Toast.LENGTH_SHORT).show();
                if(notesTitle.getText().toString()==null || notesDiscription.getText().toString()==null){
                    Toast.makeText(Newnotes.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("users").child(userMobile).child("notes").child(notesTitle.getText().toString()).child("NotesTitle").setValue(notesTitle.getText().toString());
                            databaseReference.child("users").child(userMobile).child("notes").child(notesTitle.getText().toString()).child("NotesDescription").setValue(notesDiscription.getText().toString());
                            Toast.makeText(Newnotes.this, "DataUploaded :)", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Newnotes.this, Notes.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                   /* NotesItems notesItems=new NotesItems();
                    notesItems.setNotesTitle(notesTitle.getText().toString());
                    notesItems.setNotesDescription(notesDiscription.getText().toString());
                    notesItems.setNotesTimeStamp(Timestamp.now());
                    DocumentReference documentReference;
                    documentReference=FirebaseFirestore.getInstance().collection("notes").document(userMobile).collection("my_notes").document();
                    documentReference.set(notesItems).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) Toast.makeText(Newnotes.this, "Sucessfully added", Toast.LENGTH_SHORT).show();
                            else Toast.makeText(Newnotes.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                }
            }
        });
    }
}