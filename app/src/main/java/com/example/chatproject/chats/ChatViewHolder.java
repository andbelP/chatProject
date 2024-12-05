package com.example.chatproject.chats;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.chatproject.R;

public class ChatViewHolder extends RecyclerView.ViewHolder{

    TextView chat_name_tv;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        chat_name_tv = itemView.findViewById(R.id.username_tv);
    }
}