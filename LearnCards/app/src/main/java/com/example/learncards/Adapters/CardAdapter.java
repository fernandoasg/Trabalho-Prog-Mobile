package com.example.learncards.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learncards.Entities.Card;
import com.example.learncards.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<Card> cards = new ArrayList<>();

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false);
        return new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder cardHolder, int i) {
        Card currentCard = cards.get(i);
        cardHolder.textViewSubject.setText(currentCard.getName());
        cardHolder.textViewSubject.setText(String.valueOf(currentCard.getSubject_fk()));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<Card> cards){
        this.cards = cards;
    }

    class CardHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewSubject;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_card_title);
            textViewSubject = itemView.findViewById(R.id.text_view_card_subject);

        }
    }
}
