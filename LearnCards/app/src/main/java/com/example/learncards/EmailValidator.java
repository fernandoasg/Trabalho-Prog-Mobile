package com.example.learncards;

import android.util.Patterns;

import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
