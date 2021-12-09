package com.example.myapplication.login;

import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.R;
import com.example.myapplication.login.user.UserDataConverter;
import com.example.myapplication.login.user.UserManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginbutton = findViewById(R.id.loginbutton);
        final TextInputEditText emailInput = findViewById(R.id.textInputEditText);
        final EditText passwordInput = findViewById(R.id.Password);
        final TextView wrongPtext = findViewById(R.id.wrongPtext);
        final Button newAccButton = findViewById(R.id.button2);
        final Button forgetPasswordButton = findViewById(R.id.forgotPasswordButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                try {
                    String email = Objects.requireNonNull(emailInput.getText()).toString();
                    String password = passwordInput.getText().toString();
                    UserDataConverter userConverter = new UserDataConverter();
                    if (email.equals("") || password.equals("")) {
                        wrongPtext.setText("Please enter a valid email or password");
                        wrongPtext.setVisibility(View.VISIBLE);
                    } else {
                        if (!userConverter.userSignIn(email, password)) {

                            wrongPtext.setVisibility(View.VISIBLE);
                        } else {
                            if (userConverter.checkEmailVerificationStatus()) {
                                Intent afterLoginIntent = new Intent(MainActivity.this, Homepage.class);
                                startActivity(afterLoginIntent);
                                finish();
                            } else {
                                Intent noVerificationIntent = new Intent(MainActivity.this, NoneVerifiedEmailActivity.class);
                                startActivity(noVerificationIntent);
                                finish();
                            }

                        }
                    }
                } catch(FirebaseAuthEmailException duplicateEmailException) {
                    wrongPtext.setText("The Email is Wrong");
                    wrongPtext.setVisibility(View.VISIBLE);
                } catch (FirebaseAuthInvalidCredentialsException invalidEmailException) {
                    wrongPtext.setText("Please Enter a valid Email");
                    wrongPtext.setVisibility(View.VISIBLE);
                }
            }
        });

        newAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(MainActivity.this, ActivityRegisterGui.class);
                startActivity(register_intent);
            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(MainActivity.this, forgotPaswordActivity.class);
                startActivity(register_intent);
            }
        });
    }
}