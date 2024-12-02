package com.example.chatproject.bottomnav.newchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.chatproject.databinding.FragmentNewchatBinding;

public class NewChatFragment extends Fragment {
    private FragmentNewchatBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewchatBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}