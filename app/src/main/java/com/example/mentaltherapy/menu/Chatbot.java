package com.example.mentaltherapy.menu;

import static com.example.mentaltherapy.menu.chatbot.Message.SENT_BY_BOT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mentaltherapy.menu.chatbot.BotReply;
import com.example.mentaltherapy.menu.chatbot.Message;
import com.example.mentaltherapy.menu.chatbot.MessageAdapter;
import com.example.mentaltherapy.R;
import com.example.mentaltherapy.menu.chatbot.SendMessageInBg;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chatbot extends AppCompatActivity implements BotReply {
    List<Message> msgList;
    MessageAdapter messageAdapter;
    RecyclerView rc;
    private String TAG="chatbot";
    private SessionName sessionName;
    private SessionsClient sessionsClient;
    EditText msg;
    private String uuid= UUID.randomUUID().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        msg=findViewById(R.id.msgText);
        ImageButton sendBtn=findViewById(R.id.sendBtn);
        rc=findViewById(R.id.rVchats);
        msgList=new ArrayList<>();
        messageAdapter=new MessageAdapter(msgList);
        rc.setAdapter(messageAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rc.setLayoutManager(llm);
        addToChat("Hi, I am your virtual personal mental health assistant. How are you doing today?",SENT_BY_BOT);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mmessage=msg.getText().toString().trim();
                if(mmessage.isEmpty()){
                    Toast.makeText(Chatbot.this, "Enter message", Toast.LENGTH_SHORT).show();
                }else{
                    addToChat(mmessage,Message.SENT_BY_ME);
                    msg.setText("");
                    sendMsgToBot(mmessage);
                }

            }
        });
        setUpBot();
    }
    private  void setUpBot(){
        try{
            InputStream stream=this.getResources().openRawResource(R.raw.mentalchatbot);
            GoogleCredentials credentials=GoogleCredentials.fromStream(stream).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            String projectId=((ServiceAccountCredentials) credentials).getProjectId();
            SessionsSettings.Builder settingsBuilder=SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings=settingsBuilder.setCredentialsProvider(
                    FixedCredentialsProvider.create(credentials)).build();
            sessionsClient =SessionsClient.create(sessionsSettings);
            sessionName=SessionName.of(projectId,uuid);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(TAG,"setUpBot : "+e.getMessage());
        }
    }
    private void sendMsgToBot(String message){
        QueryInput Input=QueryInput.newBuilder().setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
        new SendMessageInBg(this,sessionName,sessionsClient,Input).execute();
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
    @Override
    public void callback(DetectIntentResponse returnResponse){
        if(returnResponse!=null){
            String botReply=returnResponse.getQueryResult().getFulfillmentText().trim();
            if(!botReply.isEmpty()){
                addToChat(botReply,SENT_BY_BOT);
            }else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }
    }
}