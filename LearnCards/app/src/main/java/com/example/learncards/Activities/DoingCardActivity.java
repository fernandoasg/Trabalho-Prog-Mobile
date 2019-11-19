package com.example.learncards.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.R;
import com.example.learncards.ViewModel.CardViewModel;

public class DoingCardActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private ImageButton closeCardButton;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_card);

        progressBar = findViewById(R.id.progressBar);
        closeCardButton = findViewById(R.id.closeCardButton);

        closeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancelLessonDialog();
            }
        });

        alertDialog = getDialogBuilder();

        Bundle bundle = getIntent().getExtras();
        long cardId = 0;
        if(bundle == null){
            cardId = -1;
        }else{
            cardId = bundle.getLong("cardId");
        }

        Bundle args = new Bundle();
        args.putLong("cardId", cardId);
        Fragment cardLessonFragment = new CardLessonFragment();
        cardLessonFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_fragment_container,
                cardLessonFragment).commit();
    }

    private AlertDialog getDialogBuilder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("Cancelar aula");
        builder.setMessage("Deseja mesmo cancelar a aula atual?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        return builder.create();
    }

    public void setProgressBar(int value){
        int current = progressBar.getProgress();
        current += value;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(current, true);
        }else{
            progressBar.setProgress(current);
        }
    }

    public void hideCloseCardButton(){
        closeCardButton.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showCancelLessonDialog();
    }

    private void showCancelLessonDialog(){
        if(!alertDialog.isShowing())
            alertDialog.show();
    }

}
