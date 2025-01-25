package com.example.chatproject.bottomnav.newchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chatproject.MainActivity;
import com.example.chatproject.databinding.FragmentNewchatBinding;
import com.example.chatproject.users.User;
import com.example.chatproject.users.UserAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class NewChatFragment extends Fragment {
    private FragmentNewchatBinding binding;
    private void loadUsers(){
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        ArrayList<User> users = new ArrayList<User>();
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CountDownLatch latch = new CountDownLatch((int) snapshot.getChildrenCount()-1);
                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                    if (userSnapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        continue;
                    }
                    String userid = userSnapshot.getKey();
                    String username = userSnapshot.child("email").getValue().toString();
                    users.add(new User(username,userid));
                }
                FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                        .child("chats").get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                String chats = task.getResult().getValue().toString();
                                for(int i = 0; i < users.size(); i++){
                                    char[] tid = (users.get(i).uid+uid).toCharArray();
                                    Arrays.sort(tid);
                                    String stid = new String(tid);
                                    if(Arrays.asList(chats.split(",")).contains(stid)){
                                        users.remove(users.get(i--));
                                    }
                                }
                                binding.usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.usersRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                                binding.usersRv.setAdapter(new UserAdapter(users));
                            }
                        });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewchatBinding.inflate(inflater, container, false);
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        loadUsers();
        return binding.getRoot();
    }

}