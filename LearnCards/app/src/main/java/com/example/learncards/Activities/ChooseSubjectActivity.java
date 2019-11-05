package com.example.learncards.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learncards.Adapters.SubjectAdapter;
import com.example.learncards.Entities.Subject;
import com.example.learncards.R;
import com.example.learncards.ViewModel.SubjectViewModel;

import java.util.List;

public class ChooseSubjectActivity extends AppCompatActivity {

    private SubjectViewModel subjectViewModel;
    private List<Subject> allSubjects;
    private ImageView returnImageButton;
    private SubjectAdapter adapter;
    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        btnConfirmar = findViewById(R.id.btnConfirmar);

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        allSubjects = subjectViewModel.getAllSubjects();

        RecyclerView recyclerView = findViewById(R.id.subjects_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new SubjectAdapter();
        adapter.setSubjects(allSubjects);
        recyclerView.setAdapter(adapter);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        allSubjects = subjectViewModel.getAllSubjects();

        returnImageButton = findViewById(R.id.return_image_button);
        returnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < adapter.getSelected().size(); i++) {
                        stringBuilder.append(adapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");
                    }
                    //TODO
                    showToast(stringBuilder.toString().trim());
                } else {
                    showToast("Selecione pelo menos um assunto");
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
