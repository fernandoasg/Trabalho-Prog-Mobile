package com.example.learncards.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private RadioGroup radioGroup;
    private RadioButton answerARadio;
    private RadioButton answerBRadio;
    private RadioButton answerCRadio;
    private RadioButton answerDRadio;
    private RadioButton answerERadio;
    private Button nextFinishButton;

    private int currentStep = 0;
    private int lastStep = 0;
    private int currentQuestion = 0;
    private long cardId = 0;
    private char userCurrentAnswer = 'f';

    final Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_questions, container, false);

        questionText = view.findViewById(R.id.questionText);
        questionDescription = view.findViewById(R.id.questionDescription);
        radioGroup = view.findViewById(R.id.radioGroup);
        answerARadio = view.findViewById(R.id.answerA);
        answerARadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCurrentAnswer = 'a';
            }
        });
        answerBRadio = view.findViewById(R.id.answerB);
        answerBRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCurrentAnswer = 'b';
            }
        });
        answerCRadio = view.findViewById(R.id.answerC);
        answerCRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCurrentAnswer = 'c';
            }
        });
        answerDRadio = view.findViewById(R.id.answerD);
        answerDRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCurrentAnswer = 'd';
            }
        });
        answerERadio = view.findViewById(R.id.answerE);
        answerERadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCurrentAnswer = 'e';
            }
        });

        nextFinishButton = view.findViewById(R.id.nextQuestionButton);

        Bundle bundle = getArguments();
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
                nextFinishButton.setEnabled(false);
                boolean canContinue = checkAnswer();

                if(canContinue){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentStep += 1;
                            currentQuestion += 1;
                            checkStepAndChosseQuestion();
                            increaseProgressBar(finalStepAmount);
                        }
                    }, 3000);
                }
            }
        });

        return view;
    }

    private boolean checkAnswer(){

        int selectId = radioGroup.getCheckedRadioButtonId();
        if(selectId != -1){

            char writeAnswer = questions.get(currentQuestion).getAnswer();
            boolean acertou = writeAnswer == userCurrentAnswer;
            if(acertou){
                trocaCor(userCurrentAnswer, true);
                ShowResult("Certa resposta!!!");
            }else{
                trocaCor(userCurrentAnswer, false);
                trocaCor(writeAnswer, true);
                ShowResult("Resposta incorreta!!!");
            }

            return true;
        }else{
            ShowResult("Você precisa escolher uma alternativa!");
            return false;
        }
    }

    private void trocaCor(char op, boolean certo){
        if(op == 'f'){
            answerARadio.setTextColor(Color.BLACK);
            answerBRadio.setTextColor(Color.BLACK);
            answerCRadio.setTextColor(Color.BLACK);
            answerDRadio.setTextColor(Color.BLACK);
            answerERadio.setTextColor(Color.BLACK);
        }else if(op == 'a'){
            if(certo){
                answerARadio.setTextColor(Color.GREEN);
            }else{
                answerARadio.setTextColor(Color.RED);
            }
        }else if(op == 'b'){
            if(certo){
                answerBRadio.setTextColor(Color.GREEN);
            }else{
                answerBRadio.setTextColor(Color.RED);
            }
        }else if(op == 'c'){
            if(certo){
                answerCRadio.setTextColor(Color.GREEN);
            }else{
                answerCRadio.setTextColor(Color.RED);
            }
        }else if(op == 'd'){
            if(certo){
                answerDRadio.setTextColor(Color.GREEN);
            }else{
                answerDRadio.setTextColor(Color.RED);
            }
        }else if(op == 'e'){
            if(certo){
                answerERadio.setTextColor(Color.GREEN);
            }else{
                answerERadio.setTextColor(Color.RED);
            }
        }
    }

    private void ShowResult(String message){
        Context context = getView().getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    private void showRatingScreen(){
        Bundle args = new Bundle();
        args.putLong("cardId", cardId);
        Fragment questionsFragment = new CardRatingFragment();
        questionsFragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.card_fragment_container,
                questionsFragment).commit();
    }

    private void checkStepAndChosseQuestion(){
        if(currentStep > lastStep){
            showRatingScreen();
            return;
        }else if(currentStep == lastStep){
            nextFinishButton.setText("Finalizar");
        }
        trocaCor('f', true);
        radioGroup.clearCheck();
        nextFinishButton.setEnabled(true);

        loadQuestions();
    }

    private void loadQuestions(){
        Question question = questions.get(currentQuestion);
        int numeroAtual = currentQuestion + 1;
        questionText.setText("Pergunta " + numeroAtual);
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

