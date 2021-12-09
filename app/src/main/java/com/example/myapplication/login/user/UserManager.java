package com.example.myapplication.login.user;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserManager {
    private FirebaseAuth mAuthenticator;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    public UserManager() {
        mAuthenticator = FirebaseAuth.getInstance();
    }


    public void verifyUser() {
        mAuthenticator.getCurrentUser().sendEmailVerification();
    }


    public Task<AuthResult> createUser(String e, String p) throws FirebaseAuthInvalidCredentialsException, FirebaseAuthEmailException, FirebaseAuthWeakPasswordException {
        return mAuthenticator.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                });
    }

    public Task<AuthResult> checkUser(String e, String p) {
        return mAuthenticator.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                });
    }

    public void signOutUser() {
        FirebaseUser user = mAuthenticator.getCurrentUser();
        mAuthenticator.signOut();
    }

    public Task<Void> passwordResetEmail(String e) {
        return mAuthenticator.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}