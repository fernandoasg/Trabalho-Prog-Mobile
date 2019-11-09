package com.example.learncards.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.learncards.Entities.Card;
import com.example.learncards.R;

public class DoingCardActivity extends AppCompatActivity {

    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_card);

        Bundle bundle = getIntent().getExtras();
        card = (Card) (bundle != null ? bundle.get("Card") : null);

        //TODO:
        // 1- POPULAR NO SEEDER DO DB ALGUMAS QUESTOES PARA TESTE
        // 2- ASYNC TASK PRA PEGAR AS QUESTOES DA CARTA
        // 3- FAZER A VIEW
    }
}
