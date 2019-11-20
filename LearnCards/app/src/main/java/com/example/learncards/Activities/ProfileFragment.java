package com.example.learncards.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

import java.util.HashMap;

public class ProfileFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap user = sessionManager.getUser();

        TextView userNameText = view.findViewById(R.id.textUserName);
        TextView userEmailText = view.findViewById(R.id.textUserEmail);
        TextView userIDText = view.findViewById(R.id.textUserID);

        userNameText.setText((String) user.get("NAME"));
        userEmailText.setText( (String) user.get("EMAIL"));
        long userID = (long)user.get("ID");
        String stringUserID = Long.toString(userID);
        userIDText.setText(stringUserID);

        Button alterarButton = view.findViewById(R.id.buttonAlterarCredenciais);
        alterarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AlterarCredenciaisActivity.class);
                startActivity(i);
            }
        });

        Button buttonInviteFriends = view.findViewById(R.id.buttonInviteFriends);
        buttonInviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("not implemented");
                    builder.setMessage("Função ainda não implementada");

                    alerta = builder.create();
                    alerta.show();
            }
        });


        return view;
    }
}
