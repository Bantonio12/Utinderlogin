package com.example.myapplication.login.user;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.example.myapplication.login.user.UserManager;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;


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
            countDownLatch.await(1L, TimeUnit.SECONDS);
            return signUpTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public void setUserName(String n) {
        UserManager uManager = new UserManager();
        uManager.setDisplayName(n);
    }

    public boolean userSignIn(String e, String p) throws FirebaseAuthEmailException, FirebaseAuthInvalidCredentialsException, FirebaseAuthWeakPasswordException {
        UserManager uManager = new UserManager();
        Task<AuthResult> signInTask = uManager.checkUserEmailPassword(e, p);
        try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
            return  signInTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public boolean checkEmailVerificationStatus() {
        UserManager uManager = new UserManager();
        /*try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
            return  signInTask.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }*/
        return uManager.checkUserVerification();
    }

    public void singOutCurrentUser() {
        UserManager uManager = new UserManager();
        uManager.signOutUser();
    }

    public boolean resetPassword(String e) {
        UserManager uManger = new UserManager();
        Task<Void> reset = uManger.passwordResetEmail(e);
        try {
            countDownLatch.await(1L, TimeUnit.SECONDS);
            return  reset.isSuccessful();
        } catch (InterruptedException ie) {
            return false;
        }
    }

    public void sendVerification() {
        UserManager uManager = new UserManager();
        uManager.verifyUser();

    }
}

