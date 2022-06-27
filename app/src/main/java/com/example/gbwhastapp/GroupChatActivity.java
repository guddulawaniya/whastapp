package com.example.gbwhastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gbwhastapp.Adapters.ChatAdapter;
import com.example.gbwhastapp.databinding.ActivityGroupChatBinding;
import com.example.gbwhastapp.models.MessagesModule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_group_chat);

        getSupportActionBar().hide();

        binding.groupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessagesModule> messagesModules = new ArrayList<>();
        final String senderId = FirebaseAuth.getInstance().getUid();
        binding.groupuserName.setText("Friends Group");

        final ChatAdapter adapter = new ChatAdapter(messagesModules, this);
        binding.groupRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.groupRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Group Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesModules.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessagesModule model = dataSnapshot.getValue(MessagesModule.class);
                    messagesModules.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.groupsendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.grouptextmessage.getText().toString().length() == 0) {


                } else {
                    final String message = binding.grouptextmessage.getText().toString();
                    final MessagesModule module = new MessagesModule(senderId, message);
                    module.setTimestamp(new Date().getTime());

                    binding.grouptextmessage.setText("");
                    database.getReference().child("Group Chat")
                            .push()
                            .setValue(module)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {

                                }
                            });

                }
            }
        });

    }
}