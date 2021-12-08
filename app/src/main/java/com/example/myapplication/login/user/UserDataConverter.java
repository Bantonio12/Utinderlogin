package com.example.myapplication.login.user;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UserDataConverter {
    final private String email;
    final private String password;
    final CountDownLatch countDownLatch = new CountDownLatch(1) ;

    public UserDataConverter(String e, String p) {
        this.email = e;
        this.password = p;
    }

    public boolean createNewUser() throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        Task<AuthResult> signUpTask = uManager.createUser(email, password);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
            return  signUpTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public boolean userSignIn() throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        Task<AuthResult> signInTask = uManager.checkUser(email, password);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
            return  signInTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }
}
