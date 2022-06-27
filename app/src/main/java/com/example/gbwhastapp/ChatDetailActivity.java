package com.example.gbwhastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gbwhastapp.Adapters.ChatAdapter;
import com.example.gbwhastapp.databinding.ActivityChatDetailBinding;
import com.example.gbwhastapp.models.MessagesModule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();


        final String senderId = auth.getUid();
        String recieverId = getIntent().getStringExtra("u3546534256serId");
        String userName = getIntent().getStringExtra("userName");
        String profilepic = getIntent().getStringExtra("profilepic");

        binding.userName.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.user).into(binding.profileImage);



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessagesModule> messagesModules = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messagesModules,this,recieverId);
        binding.ChatRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.ChatRecyclerView.setLayoutManager(linearLayoutManager);
        final String senderRoom = senderId+recieverId;
        final String recieverRoom = recieverId+senderId;


        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModules.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            MessagesModule module = snapshot1.getValue(MessagesModule.class);
                            module.setMesaageId(snapshot1.getKey());
                            messagesModules.add(module);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        binding.sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( binding.textmessage.getText().toString().length() == 0 )
                {


                }
                else {
                    String message = binding.textmessage.getText().toString();
                    final MessagesModule module = new MessagesModule(senderId, message);
                    module.setTimestamp(new Date().getTime());
                    binding.textmessage.setText("");

                    database.getReference().child("chats")
                            .child(senderRoom)
                            .push()
                            .setValue(module).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {
                                    database.getReference().child("chats")
                                            .child(recieverRoom)
                                            .push()
                                            .setValue(module).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(@NonNull Void unused) {

                                                }
                                            });

                                }
                            });
                }
            }
        });
    }
}