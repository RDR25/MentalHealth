package com.example.mentaltherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Chatbot extends AppCompatActivity {
    List<Message> msgList;
    MessageAdapter messageAdapter;
    RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        EditText msg=findViewById(R.id.msgText);
        ImageButton sendBtn=findViewById(R.id.sendBtn);
        rc=findViewById(R.id.rVchats);
        msgList=new ArrayList<>();

        messageAdapter=new MessageAdapter(msgList);
        rc.setAdapter(messageAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rc.setLayoutManager(llm);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mmessage=msg.getText().toString().trim();
                if(mmessage.isEmpty()){
                    Toast.makeText(Chatbot.this, "Enter message", Toast.LENGTH_SHORT).show();
                }else{
                    addToChat(mmessage,Message.SENT_BY_ME);
                    msg.setText("");
                }

            }
        });
    }
    void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msgList.add(new Message(message,sentBy));
                messageAdapter.notifyDataSetChanged();
                rc.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });

    }
}