package com.example.learncards.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.R;
import com.example.learncards.ViewModel.CardViewModel;

public class DoingCardActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private ImageButton closeCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_card);

        progressBar = findViewById(R.id.progressBar);
        closeCardButton = findViewById(R.id.closeCardButton);

        Bundle bundle = getIntent().getExtras();
        long cardId = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        Bundle args = new Bundle();
        args.putLong("cardId", cardId);
        Fragment cardLessonFragment = new CardLessonFragment();
        cardLessonFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_fragment_container,
                cardLessonFragment).commit();
    }

    public void setProgressBar(int value){
        int current = progressBar.getProgress();
        current += value;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(current, true);
        }else{
            progressBar.setProgress(current);
        }
    }
}
