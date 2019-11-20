package com.example.learncards.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.learncards.Adapters.CardAdapter;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.Entities.Subject;
import com.example.learncards.R;
import com.example.learncards.SessionManager;
import com.example.learncards.ViewModel.CardViewModel;
import com.example.learncards.ViewModel.SubjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class CardsListFragment extends Fragment {

    private List<CardWithQuestions> userCards;
    private SessionManager sessionManager;
    private ImageButton chooseSubjectsPlusButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);

        userCards = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recicler_view_cards);
        chooseSubjectsPlusButton = view.findViewById(R.id.chooseSubjectsPlusButton);
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        long userID = (long) sessionManager.getUser().get("ID");

        SubjectViewModel subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        List<Subject> userSubjects = subjectViewModel.getUserSubjects(userID);

        CardViewModel cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        for(Subject subject : userSubjects){
            List<CardWithQuestions> cards = cardViewModel.getCardsFromMySubjects(subject.getId());
            for(CardWithQuestions card : cards){
                card.card.setSubjectName(subject.getName());
                userCards.add(card);
            }
        }

        CardAdapter adapter = new CardAdapter(userCards, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        chooseSubjectsPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChooseSubjectActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
