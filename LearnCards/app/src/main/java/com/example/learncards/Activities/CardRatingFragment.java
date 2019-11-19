package com.example.learncards.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learncards.R;
import com.example.learncards.SessionManager;

public class CardRatingFragment extends Fragment {

    private Button finishButton;

    private SessionManager sessionManager;
    private long cardId = 0;
    private long userID;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_rating, container, false);

        finishButton = view.findViewById(R.id.finishButton);

        Activity activity = getActivity();
        if(activity instanceof DoingCardActivity)
            ((DoingCardActivity) activity).hideCloseCardButton();

        Bundle bundle = getArguments();
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        userID = (long) sessionManager.getUser().get("ID");

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO Salvar que fez a aula, a nota e o comentário

                Intent i = new Intent(view.getContext(), HomeActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });


        return view;
    }
}