package com.example.learncards.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.R;
import com.example.learncards.ViewModel.CardViewModel;

import org.w3c.dom.Text;

public class CardLessonFragment extends Fragment {

    private TextView lessonSubject;
    private TextView descriptionTitle;
    private TextView descriptionText;

    private TextView applicationTitle;
    private TextView applicationText;

    Button questionsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_lesson, container, false);

        lessonSubject = view.findViewById(R.id.lessonSubject);
        descriptionTitle = view.findViewById(R.id.descriptionTitle);
        descriptionText = view.findViewById(R.id.descriptionText);

        applicationTitle = view.findViewById(R.id.applicationTitle);
        applicationText = view.findViewById(R.id.applicationText);

        questionsButton = view.findViewById(R.id.questionsButton);

        Bundle bundle = getArguments();
        long cardId = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        CardWithQuestions card;
        CardViewModel cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        card = cardViewModel.getCard(cardId);
        setElementsContent(card.card);

        final long finalCardId = cardId;
        questionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putLong("cardId", finalCardId);
                Fragment cardLessonFragment = new CardLessonFragment();
                cardLessonFragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.card_fragment_container,
                        cardLessonFragment).commit();
            }
        });

        return view;
    }

    private void setElementsContent(Card card){
        lessonSubject.setText(card.getName());
        descriptionTitle.setText("Descrição");
        descriptionText.setText(card.getDescription());
        applicationTitle.setText("Exemplo");
        applicationText.setText(card.getContext());
    }
}