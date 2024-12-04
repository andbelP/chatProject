package com.example.chatproject.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatproject.R;

import java.util.ArrayList;
public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> users = new ArrayList<>();
    public UserAdapter(ArrayList<User> users){
        this.users = users;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv, parent, false);
        return new UserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.username_tv.setText(users.get(position).email);
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
}
