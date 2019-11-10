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
        long cardId = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        System.out.println("DEVO PEGAR O CARD DE ID = " + cardId);
        System.out.println("DEVO PEGAR O CARD DE ID = " + cardId);
        System.out.println("DEVO PEGAR O CARD DE ID = " + cardId);
        System.out.println("DEVO PEGAR O CARD DE ID = " + cardId);
        System.out.println("DEVO PEGAR O CARD DE ID = " + cardId);

        //TODO:
        // 1- POPULAR NO SEEDER DO DB ALGUMAS QUESTOES PARA TESTE
        // 2- ASYNC TASK PRA PEGAR AS QUESTOES DA CARTA
        // 3- FAZER A VIEW
    }
}
