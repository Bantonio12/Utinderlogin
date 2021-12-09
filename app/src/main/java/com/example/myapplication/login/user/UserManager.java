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

import com.google.firebase.auth.UserProfileChangeRequest;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserManager {
    private FirebaseAuth mAuthenticator;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    public UserManager() {
        mAuthenticator = FirebaseAuth.getInstance();
    }


    public void verifyUser() {
        Objects.requireNonNull(mAuthenticator.getCurrentUser()).sendEmailVerification();
    }



    public Task<AuthResult> createUser(String e, String p) throws FirebaseAuthInvalidCredentialsException, FirebaseAuthEmailException, FirebaseAuthWeakPasswordException {
        return mAuthenticator.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                });
    }

    public Task<AuthResult> checkUserEmailPassword(String e, String p) {
        return mAuthenticator.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(task -> {
                });
    }

    public boolean checkUserVerification() {
        return Objects.requireNonNull(mAuthenticator.getCurrentUser()).isEmailVerified();
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

    public void setDisplayName(String n) {
        UserProfileChangeRequest updatingUserName = new UserProfileChangeRequest.Builder()
                .setDisplayName(n).build();
        mAuthenticator.getCurrentUser().updateProfile(updatingUserName).addOnCompleteListener(task -> {

        });
    }
}