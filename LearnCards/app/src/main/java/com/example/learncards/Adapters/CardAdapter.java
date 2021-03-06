package com.example.learncards.Adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learncards.Activities.DoingCardActivity;
import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<CardWithQuestions> cards;
    private Context context;

    public CardAdapter(List<CardWithQuestions> cards, Context context) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false);
        return new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder cardHolder, int i) {
        CardWithQuestions currentCard = cards.get(i);
        if(currentCard.done)
            cardHolder.cardBackground.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorCardDone));
        cardHolder.textViewTitle.setText(currentCard.card.getName());
        cardHolder.textViewSubject.setText(String.valueOf(currentCard.card.getSubjectName()));
        cardHolder.bind(cards.get(i));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewSubject;
        private CardView cardBackground;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            cardBackground = itemView.findViewById(R.id.cardBackground);
            textViewTitle = itemView.findViewById(R.id.text_view_card_title);
            textViewSubject = itemView.findViewById(R.id.text_view_card_subject);
        }

        void bind(final CardWithQuestions card) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DoingCardActivity.class);
                    intent.putExtra("cardId" , card.card.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
