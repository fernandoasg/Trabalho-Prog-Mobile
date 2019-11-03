package com.example.learncards.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Repositories.SubjectRepository;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {

    private SubjectRepository repository;
    private List<Subject> allSubjects;

    public SubjectViewModel(@NonNull Application application){
        super(application);
        repository = new SubjectRepository(application);
        allSubjects = repository.getAllSubjecties();
    }

    public List<Subject> getAllSubjects(){
        return allSubjects;
    }
}
