package com.example.learncards.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Repositories.SubjectRepository;
import com.example.learncards.SessionManager;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {

    private SubjectRepository subjectRepository;

    public SubjectViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = new SubjectRepository(application);
    }

    //TODO FAZER RETORNAR, para isso criar o metodo no repositorio e o metodo no subjectDAO: subjectRepository.getUserSubjects(userID);
    public List<Subject> getUserSubjects(long userID) {
        return null;
    }

    public List<Subject> getAllNonSelectedSubjects(long userID) {
        return subjectRepository.getAllNonSelectedSubjects(userID);
    }
}
