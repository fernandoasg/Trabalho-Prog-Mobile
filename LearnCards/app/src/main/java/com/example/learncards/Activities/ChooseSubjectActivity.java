package com.example.learncards.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.learncards.Adapters.SubjectAdapter;
import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.SubjectWithCards;
import com.example.learncards.R;
import com.example.learncards.ViewModel.SubjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseSubjectActivity extends AppCompatActivity {

    private SubjectViewModel subjectViewModel;
    private List<Subject> allSubjects;
    private ImageView returnImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        allSubjects = subjectViewModel.getAllSubjects();

        RecyclerView recyclerView = findViewById(R.id.subjects_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SubjectAdapter adapter = new SubjectAdapter();
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
    }
}
