package com.example.myapplication.login.user;

import com.example.myapplication.login.user.UserManager;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class UserDataConverter {
    final private String email;
    final private String password;

    public UserDataConverter(String e, String p) {
        this.email = e;
        this.password = p;
    }

    public boolean createNewUser() throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        return uManager.createUser(email, password) == 1;
    }

    public boolean userSignIn() throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        return uManager.checkUser(email, password);
    }
}
