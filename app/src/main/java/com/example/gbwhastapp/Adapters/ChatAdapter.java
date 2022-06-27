package com.example.gbwhastapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbwhastapp.R;
import com.example.gbwhastapp.models.MessagesModule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<MessagesModule> messagesModules;
    Context context;
    String recId;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;



    public ChatAdapter(ArrayList<MessagesModule> messagesModules, Context context) {
        this.messagesModules = messagesModules;
        this.context = context;

    }


    public ChatAdapter(ArrayList<MessagesModule> messagesModules, Context context, String recId) {
        this.messagesModules = messagesModules;
        this.context = context;
        this.recId = recId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new senderViewholder(view);
        }
        else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return new recieverViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(messagesModules.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else
        {
            return RECIEVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessagesModule messagemodule = messagesModules.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you  sure want to delete this message ")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid()+recId;


                                database.getReference().child("chats").child(senderRoom)
                                        .child(messagemodule.getMesaageId())
                                        .setValue(null);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int watch) {
                        dialog.dismiss();


                    }
                }).show();

                return false;
            }
        });

        if(holder.getClass()==senderViewholder.class)
        {
            ((senderViewholder) holder).senderMsg.setText(messagemodule.getMessage());
        }
        else{
            ((recieverViewHolder)holder).recieverMsg.setText(messagemodule.getMessage());
        }


    }

    @Override
    public int getItemCount() {


        return messagesModules.size();
    }

    public class recieverViewHolder extends RecyclerView.ViewHolder{
        TextView recieverMsg , reciverTime;

        public recieverViewHolder(@NonNull View itemView) {
            super(itemView);

            recieverMsg = itemView.findViewById(R.id.recieverText);
            reciverTime = itemView.findViewById(R.id.recieverTime);
        }
    }
}

class senderViewholder extends RecyclerView.ViewHolder{
    TextView senderMsg,senderTime;
    public senderViewholder(@NonNull View itemView) {

        super(itemView);
        senderMsg = itemView.findViewById(R.id.senderText);
        senderTime = itemView.findViewById(R.id.senderTime);

    }
}
