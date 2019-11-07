package com.example.learncards.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learncards.Entities.Subject;
import com.example.learncards.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<Subject> subjects = new ArrayList<>();

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subject_item, viewGroup, false);
        return new SubjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder subjectHolder, int i) {
        Subject currentSubject = subjects.get(i);
        subjectHolder.textViewTitle.setText(currentSubject.getName());
        subjectHolder.textViewSubArea.setText(currentSubject.getSubArea());
        subjectHolder.bind(subjects.get(i));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSelected() {
        ArrayList<Subject> selected = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).isChecked()) {
                selected.add(subjects.get(i));
            }
        }
        return selected;
    }

    class SubjectHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewSubArea;
        private ImageView checkedTick;

        SubjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.subject_title_text);
            textViewSubArea = itemView.findViewById(R.id.sub_area_text);
            checkedTick = itemView.findViewById(R.id.imageView);
        }

        void bind(final Subject subject) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subject.setChecked(!subject.isChecked());
                    checkedTick.setVisibility(subject.isChecked() ? View.VISIBLE : View.GONE);
                }
            });
        }
    }
}
