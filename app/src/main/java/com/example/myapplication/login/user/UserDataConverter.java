package com.example.myapplication.login.user;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.example.myapplication.login.user.UserManager;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UserDataConverter {
    final CountDownLatch countDownLatch = new CountDownLatch(1) ;

    public UserDataConverter() {
    }

    public boolean createNewUser(String e, String p) throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        Task<AuthResult> signUpTask = uManager.createUser(e, p);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
            return  signUpTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public boolean userSignIn(String e, String p) throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        Task<AuthResult> signInTask = uManager.checkUser(e, p);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
            return  signInTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public void singOutCurrentUser() {
        UserManager uManager = new UserManager();
        uManager.signOutUser();
    }
}

