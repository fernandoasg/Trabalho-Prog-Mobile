package com.example.learncards.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardsDone;
import com.example.learncards.R;
import com.example.learncards.SessionManager;
import com.example.learncards.ViewModel.CardsDoneViewModel;

public class CardRatingFragment extends Fragment {

    private CardsDoneViewModel cardsDoneViewModel;
    private TextView userCommentText;
    private RatingBar ratingBar;
    private Button finishButton;

    private SessionManager sessionManager;
    private long cardId = 0;
    private long userID;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_rating, container, false);

        finishButton = view.findViewById(R.id.finishButton);
        userCommentText = view.findViewById(R.id.comentario);
        ratingBar = view.findViewById(R.id.ratingBar);

        cardsDoneViewModel = ViewModelProviders.of(this).get(CardsDoneViewModel.class);

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

                float numStars = ratingBar.getNumStars();
                String userComment = userCommentText.getText().toString();

                CardsDone cardsDone = new CardsDone(numStars, userComment, userID, cardId);
                cardsDoneViewModel.saveCardDone(cardsDone);

                ShowResult("Aula conclúida!");
                Intent i = new Intent(view.getContext(), HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        return view;
    }

    private void ShowResult(String message){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getView().findViewById(R.id.toast_layout_root));
        ImageView image = layout.findViewById(R.id.toast_image);
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(getView().getContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}