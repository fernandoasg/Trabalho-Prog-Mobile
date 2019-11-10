package com.example.learncards.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.R;
import com.example.learncards.ViewModel.CardViewModel;

public class DoingCardActivity extends AppCompatActivity {

    private CardWithQuestions card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_card);

        Bundle bundle = getIntent().getExtras();
        long cardId = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        CardViewModel cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        card = cardViewModel.getCard(cardId);

        Bundle args = new Bundle();
        args.putLong("cardId", cardId);
        Fragment cardLessonFragment = new CardLessonFragment();
        cardLessonFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_fragment_container,
                cardLessonFragment).commit();
    }
}
