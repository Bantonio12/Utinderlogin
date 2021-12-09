package com.example.myapplication.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.login.user.UserDataConverter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class ActivityRegisterGui extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gui);

        final Button registerButton = findViewById(R.id.registerButton);
        final TextView email_input = findViewById(R.id.email_input);
        final TextView nickname_input = findViewById(R.id.nicknameInput);
        final EditText password_input = findViewById(R.id.newUserPassword);
        final TextView warning_message = findViewById(R.id.same_acc_text);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String email = email_input.getText().toString();
                String name = nickname_input.getText().toString();
                String password = password_input.getText().toString();
                UserDataConverter uDataConverter = new UserDataConverter();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();

                if (email.equals("") || password.equals("")) {
                    warning_message.setText("Please enter a valid Email or a valid Password");
                    warning_message.setVisibility(View.VISIBLE);
                } else {
                    try {
                        boolean temp = uDataConverter.createNewUser(email, password);

                        if (temp) {
                            uDataConverter.sendVerification();
                            uDataConverter.setUserName(name);
                            Intent successfully_registered = new Intent(ActivityRegisterGui.this, ActivityAfterLogin.class);
                            startActivity(successfully_registered);
                            finish();
                        } else {
                            warning_message.setText("Account could not be created");
                            warning_message.setVisibility(View.VISIBLE);
                        }
                /*} else {
                    warning_message.setVisibility(View.VISIBLE);
                }*/
                    } catch(FirebaseAuthWeakPasswordException d) {
                        warning_message.setText("Password is weak");
                        warning_message.setVisibility(View.VISIBLE);
                    } catch(FirebaseAuthEmailException duplicateEmailException) {
                        warning_message.setText("This Email is already used");
                        warning_message.setVisibility(View.VISIBLE);
                    } catch (FirebaseAuthInvalidCredentialsException invalidEmailException) {
                        warning_message.setText("Please Enter a valid Email");
                        warning_message.setVisibility(View.VISIBLE);
                    }
                }


            }
        });
    }
}