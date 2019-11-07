package com.example.learncards.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Repositories.SubjectRepository;
import com.example.learncards.SessionManager;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {

    private List<Subject> allSubjects;
    private List<Subject> allNonSelectedSubjects;

    public SubjectViewModel(@NonNull Application application){
        super(application);
        SessionManager sm = new SessionManager(application);
        long userID = (long) sm.getUser().get("ID");
        SubjectRepository repository = new SubjectRepository(application);
        allSubjects = repository.getAllSubjecties();
        allNonSelectedSubjects = repository.getAllNonSelectedSubjects(userID);
    }

    public List<Subject> getAllSubjects(){
        return allSubjects;
    }

    public List<Subject> getAllNonSelectedSubjects(){
        return allNonSelectedSubjects;
    }
}
