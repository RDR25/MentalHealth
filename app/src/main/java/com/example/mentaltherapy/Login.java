package com.example.mentaltherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mentaltherapy.splash.LoadingSplash;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://mentaltherapy-b1c38-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout LoginmobileNumber=findViewById(R.id.mobileNumber);
        final TextInputLayout LoginPassword=findViewById(R.id.password);
        Button Loginbtn=findViewById(R.id.loginLoginbtn);
        Button Registerbtn=findViewById(R.id.loginRegisterbtn);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mobileNumberText= Objects.requireNonNull(LoginmobileNumber.getEditText()).getText().toString();
                final String passwordText= Objects.requireNonNull(LoginPassword.getEditText()).getText().toString();

                if(mobileNumberText.isEmpty()||passwordText.isEmpty()){
                    Toast.makeText(Login.this, "Fill all the details", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(mobileNumberText)){
                                final String getPassword=snapshot.child(mobileNumberText).child("password").getValue(String.class);
                                if(getPassword.equals(passwordText)){
                                    startActivity(new Intent(Login.this, LoadingSplash.class));
                                }else{
                                    Toast.makeText(Login.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(Login.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}