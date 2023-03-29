package com.example.mentaltherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Register extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://mentaltherapy-b1c38-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final TextInputLayout userName=findViewById(R.id.registerUsername);
        final TextInputLayout email=findViewById(R.id.registerEmail);
        final TextInputLayout mobileNumber=findViewById(R.id.registermobileNumber);
        final TextInputLayout password=findViewById(R.id.registerpassword);
        Button lloginbtn=findViewById(R.id.registerLoginBtn);
        Button rregisterbtn=findViewById(R.id.registerRegisterBtn);
        lloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        rregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameText= Objects.requireNonNull(userName.getEditText()).getText().toString();
                final String emailText= Objects.requireNonNull(email.getEditText()).getText().toString();
                final String passwordText= Objects.requireNonNull(password.getEditText()).getText().toString();
                final String mobilenumberText= Objects.requireNonNull(mobileNumber.getEditText()).getText().toString();
                if(usernameText.isEmpty()||emailText.isEmpty()||mobilenumberText.isEmpty()||passwordText.isEmpty()){
                    Toast.makeText(Register.this,"Enter all Details",Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(mobilenumberText)){
                                Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("users").child(mobilenumberText).child("username").setValue(usernameText);
                                databaseReference.child("users").child(mobilenumberText).child("email").setValue(emailText);
                                databaseReference.child("users").child(mobilenumberText).child("password").setValue(passwordText);
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,LoadingSplash.class));
                                finish();
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