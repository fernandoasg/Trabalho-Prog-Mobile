package com.example.learncards.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learncards.Adapters.SubjectAdapter;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.UserSubject;
import com.example.learncards.R;
import com.example.learncards.SessionManager;
import com.example.learncards.ViewModel.SubjectViewModel;

import java.util.List;

public class ChooseSubjectActivity extends AppCompatActivity {

    private SubjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        SubjectViewModel subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        List<Subject> allSubjects = subjectViewModel.getAllNonSelectedSubjects();

        RecyclerView recyclerView = findViewById(R.id.subjects_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new SubjectAdapter();
        adapter.setSubjects(allSubjects);
        recyclerView.setAdapter(adapter);

        ImageView returnImageButton = findViewById(R.id.return_image_button);
        returnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveSelectedCardsTask().execute();
            }
        });
    }

    class SaveSelectedCardsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            SessionManager sessionManager = new SessionManager(getApplicationContext());
            AppDatabase mydb = AppDatabase.getInstance(LearnCards.getAppContext());
            long userID = (long) sessionManager.getUser().get("ID");

            if (adapter.getSelected().size() > 0) {
                for (int i = 0; i < adapter.getSelected().size(); i++) {
                    mydb.userSubjectDao().insert(
                            new UserSubject(userID, adapter.getSelected().get(i).getId()));
                }
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
            return null;
        }
    }
}
