package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.User;
import com.example.learncards.Repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private List<User> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }

    public void update(User user){
        repository.update(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteAllUsers(){
        repository.deleteAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<Subject> getAllMySubjects(Long userId){
        return repository.getAllMySubjects(userId);
    }
}
