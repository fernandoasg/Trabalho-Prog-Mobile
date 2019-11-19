package com.example.learncards.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
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

    final Handler handler = new Handler();

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
                nextFinishButton.setActivated(false);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentStep += 1;
                        currentQuestion += 1;
                        checkStepAndChosseQuestion();
                    }
                }, 3000);
            }
        });

        return view;
    }

    private void checkAnswer(){
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
        System.out.println("Verificar se acertou ou errou");
        ShowResult("Mostra se acertou ou errou");
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
        nextFinishButton.setActivated(true);

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

