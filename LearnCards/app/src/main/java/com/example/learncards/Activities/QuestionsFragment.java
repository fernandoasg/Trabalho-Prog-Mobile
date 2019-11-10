package com.example.learncards.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.Entities.Question;
import com.example.learncards.R;
import com.example.learncards.ViewModel.CardViewModel;

import java.util.List;

public class QuestionsFragment extends Fragment {

    private List<Question> questions;

    private TextView questionText;
    private TextView questionDescription;
    private RadioButton answerARadio;
    private RadioButton answerBRadio;
    private RadioButton answerCRadio;
    private RadioButton answerDRadio;
    private RadioButton answerERadio;
    private Button nextFinishButton;

    private int currentStep = 0;
    private int lastStep = 0;
    private int currentQuestion = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_questions, container, false);

        questionText = view.findViewById(R.id.questionText);
        questionDescription = view.findViewById(R.id.questionDescription);
        answerARadio = view.findViewById(R.id.answerA);
        answerBRadio = view.findViewById(R.id.answerB);
        answerCRadio = view.findViewById(R.id.answerC);
        answerDRadio = view.findViewById(R.id.answerD);
        answerERadio = view.findViewById(R.id.answerE);

        nextFinishButton = view.findViewById(R.id.nextQuestionButton);

        Bundle bundle = getArguments();
        long cardId = 0;
        int stepAmount = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
            stepAmount = bundle.getInt("stepAmount");
        }

        currentStep = 2;
        lastStep = 100/stepAmount;

        CardWithQuestions card;
        CardViewModel cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        card = cardViewModel.getCard(cardId);
        questions = card.questions;

        if(currentStep > lastStep){
            showRatingScreen();
        }else{
            checkStepAndChosseQuestion();
        }

        final int finalStepAmount = stepAmount;
        nextFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentStep += 1;
                currentQuestion += 1;
                checkStepAndChosseQuestion();
                increaseProgressBar(finalStepAmount);
            }
        });

        return view;
    }

    private void checkAnswer(){
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
    }

    private void showRatingScreen(){
        System.out.println("Mostra a tela de avaliar a cartão");
        System.out.println("Mostra a tela de avaliar a cartão");
        System.out.println("Mostra a tela de avaliar a cartão");
        System.out.println("Mostra a tela de avaliar a cartão");
    }

    private void checkStepAndChosseQuestion(){
        if(currentStep > lastStep){
            showRatingScreen();
            return;
        }else if(currentStep == lastStep-1){
            nextFinishButton.setText("Finalizar");
        }

        loadQuestions();
    }

    private void loadQuestions(){
        Question question = questions.get(currentQuestion);
        questionText.setText("Pergunta " + currentQuestion+1);
        questionDescription.setText(question.getDescription());
        answerARadio.setText(question.getAlternativeA());
        answerBRadio.setText(question.getAlternativeB());
        answerCRadio.setText(question.getAlternativeC());
        answerDRadio.setText(question.getAlternativeD());
        answerERadio.setText(question.getAlternativeE());
    }

    private void increaseProgressBar(int value){
        Activity activity = getActivity();
        if(activity instanceof DoingCardActivity)
            ((DoingCardActivity) activity).setProgressBar(value);
    }
}

