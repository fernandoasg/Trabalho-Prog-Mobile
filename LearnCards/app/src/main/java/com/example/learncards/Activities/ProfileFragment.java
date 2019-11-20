package com.example.learncards.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

import java.util.HashMap;

public class ProfileFragment extends Fragment {
    TextView nameToSet, emailToSet;
    User usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap user = sessionManager.getUser();

        TextView userNameText = view.findViewById(R.id.textUserName);
        TextView userEmailText = view.findViewById(R.id.textUserEmail);
        TextView userIDText = view.findViewById(R.id.textUserID);

        userNameText.setText("Username: " + (String) user.get("NAME"));
        userEmailText.setText("Email: " + (String) user.get("EMAIL"));
        userIDText.setText("User id:" +((long) user.get("ID")));

        Button alterarButton = view.findViewById(R.id.buttonAlterarCredenciais);
        alterarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AlterarCredenciaisActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
