package com.example.mentaltherapy;

import static com.example.mentaltherapy.Login.USER;
import static com.example.mentaltherapy.menu.Meditation.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentaltherapy.splash.LoadingSplash;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddContacts extends AppCompatActivity {
    Button contactsbtn;
    TextInputLayout contactName1;
    TextInputLayout contactName2;
    TextInputLayout contactName3;
    TextInputLayout contact1;
    TextInputLayout contact2;
    TextInputLayout contact3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://mentaltherapy-b1c38-default-rtdb.firebaseio.com/");
        contactsbtn=findViewById(R.id.contactsbtn);
        contactName1=findViewById(R.id.contact1Name);
        contactName2=findViewById(R.id.contact2Name);
        contactName3=findViewById(R.id.contact3Name);
        contact1=findViewById(R.id.contact1);
        contact1=findViewById(R.id.contact2);
        contact1=findViewById(R.id.contact3);

        contactsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String contactNameText1= Objects.requireNonNull(contactName1.getEditText()).getText().toString();
                final String contactNameText2= Objects.requireNonNull(contactName2.getEditText()).getText().toString();
                final String contactNameText3= Objects.requireNonNull(contactName3.getEditText()).getText().toString();
                final String contactText1= Objects.requireNonNull(contact1.getEditText()).getText().toString();
                final String contactText2= Objects.requireNonNull(contact1.getEditText()).getText().toString();
                final String contactText3= Objects.requireNonNull(contact1.getEditText()).getText().toString();
                if(contactNameText1.isEmpty()|| contactNameText2.isEmpty()||contactNameText3.isEmpty()||contactText1.isEmpty()||contactText2.isEmpty()||contactText3.isEmpty()){
                    Toast.makeText(AddContacts.this, "Fill all the details", Toast.LENGTH_SHORT).show();
                }else{
                    sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    Toast.makeText(AddContacts.this, sharedPreferences.getString(USER,""), Toast.LENGTH_SHORT).show();
                    databaseReference.child("users").child(sharedPreferences.getString(USER,"")).child("contact1").setValue(contactNameText1+":"+contactText1);
                    databaseReference.child("users").child(sharedPreferences.getString(USER,"")).child("contact2").setValue(contactNameText2+":"+contactText2);
                    databaseReference.child("users").child(sharedPreferences.getString(USER,"")).child("contact3").setValue(contactNameText3+":"+contactText3);
                    Toast.makeText(AddContacts.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddContacts.this, LoadingSplash.class));
                    finish();
                }
            }
        });
    }
}