package com.example.learncards.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learncards.R;

public class NoSubjectFragment extends Fragment {

    private Button chooseSubjectsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_no_subject, container, false);

        chooseSubjectsButton = v.findViewById(R.id.chooseSubjectsButton);
        chooseSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChooseSubjectActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
