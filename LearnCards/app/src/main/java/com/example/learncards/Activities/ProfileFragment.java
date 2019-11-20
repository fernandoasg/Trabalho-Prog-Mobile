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
import android.widget.Toast;

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
        userIDText.setText("User ID:" +((long) user.get("ID")));

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
                    //Cria o gerador do AlertDialog
                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //define o titulo
                    builder.setTitle("not implemented");
                    //define a mensagem
                    builder.setMessage("Função ainda não implementada");
                    //define um botão como positivo

                    alerta = builder.create();
                    alerta.show();
            }
        });


        return view;
    }
}
