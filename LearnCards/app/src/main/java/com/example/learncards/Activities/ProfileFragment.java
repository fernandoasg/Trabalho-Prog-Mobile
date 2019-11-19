package com.example.learncards.Activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learncards.Entities.User;
import com.example.learncards.R;

public class ProfileFragment extends Fragment {
    TextView nameToSet, emailToSet;
    User usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        nameToSet = v.findViewById(R.id.NameToSet);
        emailToSet = v.findViewById(R.id.EmailToSet);

        //TODO load o usuario logado

        nameToSet.setText(usuario.getName());
        emailToSet.setText(usuario.getEmail());

        return v;
    }
}
