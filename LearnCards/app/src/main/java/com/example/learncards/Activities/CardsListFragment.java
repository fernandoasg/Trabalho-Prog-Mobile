package com.example.learncards.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learncards.Adapters.CardAdapter;
import com.example.learncards.Entities.Card;
import com.example.learncards.R;

import java.util.List;

public class CardsListFragment extends Fragment {

    private List<Card> userCards;

    CardsListFragment(List<Card> userCards) {
        this.userCards = userCards;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_list, container, false);

        CardAdapter adapter = new CardAdapter(userCards, getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recicler_view_cards);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
