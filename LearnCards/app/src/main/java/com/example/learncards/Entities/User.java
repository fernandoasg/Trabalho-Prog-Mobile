package com.example.learncards.Entities;

public class User {

    private long id;
    private String name;
    private String email;
    private String password;

    public User(long i, String n, String e, String p){
        this.id = i;
        this.name = n;
        this.email = e;
        this.password = p;
    }

}
