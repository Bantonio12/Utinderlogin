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


    /*public boolean findUsername(String n) {
        final boolean[] usernameExists = {false};
        Query usernameDoc = database.collection("users").whereIn("username",
                Collections.singletonList(n));
        usernameDoc.whereIn("username", Collections.singletonList(n)).get();
        if (usernameDoc.whereIn("username", Collections.singletonList(n)).get() != "") {

        }
        *//*documentReading.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    System.out.println("w");
                    if (doc.exists()) {
                        usernameExists[0] = true;
                        System.out.println("F");
                    }
                }
                System.out.println("L");
            }
        });
        return usernameExists[0];*//*
    }*/

    /*public String getPassword(String n) {
        final String[] password = {""};
        DocumentReference documentReading = database.collection("users")
                .document("UserNamePassword");
        documentReading.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        password[0] = doc.get(n).toString();
                    }
                }
            }
        });
        return password[0];
    }

    public String getEmail(String n) {
        final String[] email = {""};
        DocumentReference documentReading = database.collection("users")
                .document("UserEmail");
        documentReading.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        email[0] = doc.get(n).toString();
                    }
                }
            }
        });
        return email[0];
    }*/


    public Task<AuthResult> createUser(String e, String p) throws FirebaseAuthInvalidCredentialsException, FirebaseAuthEmailException, FirebaseAuthWeakPasswordException {
        return mAuthenticator.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }

    public Task<AuthResult> checkUser(String e, String p) throws FirebaseAuthInvalidCredentialsException, FirebaseAuthEmailException, FirebaseAuthWeakPasswordException {
        return mAuthenticator.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }

    public void signOutUser() {
        FirebaseUser user = mAuthenticator.getCurrentUser();
        mAuthenticator.signOut();

    }
}